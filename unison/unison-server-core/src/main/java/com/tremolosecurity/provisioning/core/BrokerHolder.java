/*
Copyright 2015 Tremolo Security, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/


package com.tremolosecurity.provisioning.core;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.jms.Connection;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.store.kahadb.KahaDBPersistenceAdapter;
import org.apache.logging.log4j.Logger;

import com.tremolosecurity.config.util.ConfigManager;
import com.tremolosecurity.openunison.OpenUnisonConstants;
import com.tremolosecurity.provisioning.core.ProvisioningException;
import com.tremolosecurity.server.StopableThread;

public class BrokerHolder {
	
	static Logger logger = org.apache.logging.log4j.LogManager.getLogger(BrokerHolder.class.getName());
	
	BrokerService broker;
	private ConfigManager cfgMgr;
	
	private Session dlqsession;
	private Queue dlq;
	private MessageConsumer mcdlq;
	private HashMap<String, MessageProducer> qs;
	static BrokerHolder holder;
	
	public static void reset() {
		holder = null;
	}
	
	public static synchronized BrokerService getInstance(ConfigManager cfgMgr,String name,ProvisioningEngineImpl engine) throws ProvisioningException {
		if (holder != null) {
			logger.info("Broker already initialized");
			return holder.broker;
		} else {
			
			String path =  System.getProperty(OpenUnisonConstants.UNISON_CONFIG_ACTIVEMQDIR);
			
			File f = new File(path + File.separator + "unison-mq-" + name);
			if (! f.exists()) {
				f.mkdir();
			}
			
			logger.info("Starting KahaDB with path " + f.getAbsolutePath());
			
			BrokerService littleBroker;
			
			
			KahaDBPersistenceAdapter kdb = new KahaDBPersistenceAdapter();
			
			
			
			kdb.setDirectory(f);
			
			

			
			
			littleBroker = new BrokerService();
			
			littleBroker.setBrokerName(name);
			littleBroker.setPersistent(true);
			
			
			f = new File(System.getProperty("java.io.tmpdir") + File.separator + "unison-tmp-mq-" + name);
			if (! f.exists()) {
				f.mkdir();
			}
			
			littleBroker.setTmpDataDirectory(f);
			try {
				littleBroker.setPersistenceAdapter(kdb);
			} catch (IOException e1) {
				throw new ProvisioningException("Could not initialize",e1);
			}
			
			StopableThread st = new BrokerThread(littleBroker,engine);
			
			cfgMgr.addThread(st);
			Thread t = new Thread(st);
			t.start();
			
			while (! littleBroker.isStarted()) {
				logger.info("Waiting for broker to start...");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					
				}
			}
			
			BrokerHolder littleholder = new BrokerHolder();
			littleholder.cfgMgr = cfgMgr;
			littleholder.broker = littleBroker;
			
			try {
				littleholder.initDLQ();
			} catch (Exception e) {
				throw new ProvisioningException("Could not initiate DLQ checker",e);
			}
			holder = littleholder;
			
			
			
			
			return holder.broker;
			
		}
	}
	
	private void initDLQ() throws Exception {
		
		javax.jms.Connection con;
		
		if (this.cfgMgr.getProvisioningEngine() == null) {
			ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory("vm://localhost/localhost");
			con = cf.createConnection();
			con.start();
		} else {
			con = this.cfgMgr.getProvisioningEngine().getQueueConnection();
		}
		
		this.dlqsession = con.createSession(true, Session.AUTO_ACKNOWLEDGE);
		this.dlq = dlqsession.createQueue("ActiveMQ.DLQ");
		this.mcdlq = dlqsession.createConsumer(dlq);
		this.qs = new HashMap<String,MessageProducer>();
		
		
		
	}
	
	public static void doClearDLQ() throws Exception {
		if (holder != null) {
			holder.clearDLQ();
		}
	}
	
	private synchronized  void clearDLQ() throws Exception {
		
		Thread.sleep(3000);
		
		Message m = null;
		while ((m = mcdlq.receiveNoWait()) != null) {
							Enumeration enumer = m.getPropertyNames();
							
							
					/*while (enumer.hasMoreElements()) {
						String name = (String) enumer.nextElement();
						System.err.println(name + "='" + m.getObjectProperty(name) + "'");
					}*/
					
					String originalQueue = m.getStringProperty("OriginalQueue");
					logger.info("Adding message " + m.getJMSMessageID() + " to queue " + originalQueue);
					
					m.acknowledge();
					dlqsession.commit();
					
					if (qs.containsKey(originalQueue)) {
						qs.get(originalQueue).send(m);
					} else {
						Queue q = dlqsession.createQueue(originalQueue);
						MessageProducer lmp = dlqsession.createProducer(q);
						qs.put(originalQueue, lmp);
						lmp.send(m);
					}
					
					
					
					
					
					
					dlqsession.commit();
		}
		
		
	}
	
	
}