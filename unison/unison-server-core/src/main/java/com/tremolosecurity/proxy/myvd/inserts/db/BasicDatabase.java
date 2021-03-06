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


package com.tremolosecurity.proxy.myvd.inserts.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.StringTokenizer;

import net.sourceforge.myvd.core.NameSpace;
import net.sourceforge.myvd.inserts.jdbc.JdbcInsert;
import net.sourceforge.myvd.inserts.jdbc.JdbcPool;

import com.tremolosecurity.proxy.myvd.inserts.util.MultiNameSpaceInsert;
import com.tremolosecurity.server.GlobalEntries;

public class BasicDatabase extends MultiNameSpaceInsert implements JdbcPool {

	@Override
	public void configureNameSpaces(String name, Properties props,
			NameSpace nameSpace, ArrayList<String> nameSpaceNames,
			Properties nsProps) {
		
		boolean hasGroups = props.get("useGroups") == null || props.get("useGroups").equals("true"); 
		boolean hasUsers = props.get("useUsers") == null || props.get("useUsers").equals("true");
		boolean supportBind = props.getProperty("supportBind", "false").equalsIgnoreCase("true");
		
		
		String rootNS = name + "_root";
		String userdb = name + "_db";
		String groupdb = name + "_groupdb";
		
		String passwordColumn = null;
		
		if (supportBind) {
			passwordColumn = props.getProperty("passwordColumn");
		}
		
		
		String validationQuery = props.getProperty("validationQuery");
		boolean hasValidationQuery = validationQuery != null && ! validationQuery.isEmpty();
		
		nameSpaceNames.add(rootNS);
		nsProps.put("server." + rootNS + ".chain","entry");
		nsProps.put("server." + rootNS + ".nameSpace",nameSpace.getBase().getDN().toString());
		nsProps.put("server." + rootNS + ".weight","0");
		nsProps.put("server." + rootNS + ".entry.className","net.sourceforge.myvd.inserts.RootObject");
		
		if (hasUsers) {
			nameSpaceNames.add(userdb);
			
			if (supportBind) {
				nsProps.put("server." + userdb + ".chain","auth,jdbc");
			} else {
				nsProps.put("server." + userdb + ".chain","jdbc");
			}
		
		
			nsProps.put("server." + userdb + ".nameSpace","ou=users," + nameSpace.getBase().getDN().toString());
			nsProps.put("server." + userdb + ".weight","0");
			
			if (supportBind) {
				nsProps.put("server." + userdb + ".auth.className", "net.sourceforge.myvd.inserts.jdbc.Pbkdf2Auth");
				nsProps.put("server." + userdb + ".auth.config.sql", "SELECT " + passwordColumn + " FROM " + props.getProperty("user-table") + " WHERE " + getUidColumn(props) + " = ?");
			}
			
			
			nsProps.put("server." + userdb + ".jdbc.className","net.sourceforge.myvd.inserts.jdbc.JdbcInsert");
			nsProps.put("server." + userdb + ".jdbc.config.driver",props.get("driver"));
			nsProps.put("server." + userdb + ".jdbc.config.url",props.get("url"));
			nsProps.put("server." + userdb + ".jdbc.config.user",props.get("user"));
			nsProps.put("server." + userdb + ".jdbc.config.password",props.getProperty("password"));
			nsProps.put("server." + userdb + ".jdbc.config.maxCons",props.getProperty("maxCons"));
			nsProps.put("server." + userdb + ".jdbc.config.maxConsIdle",props.getProperty("maxConsIdle"));
			nsProps.put("server." + userdb + ".jdbc.config.rdn","uid");
			nsProps.put("server." + userdb + ".jdbc.config.mapping",props.getProperty("user-mapping"));
			nsProps.put("server." + userdb + ".jdbc.config.objectClass",GlobalEntries.getGlobalEntries().getConfigManager().getCfg().getUserObjectClass());
			nsProps.put("server." + userdb + ".jdbc.config.sql",getUserSelect(props));
			nsProps.put("server." + userdb + ".jdbc.config.addBaseToFilter","false");
			nsProps.put("server." + userdb + ".jdbc.config.useSimple","true");
			if (hasValidationQuery) {
				nsProps.put("server." + userdb + ".jdbc.config.validationQuery",validationQuery);
			}
		}
		
		if (hasGroups) {
			nameSpaceNames.add(groupdb);
			nsProps.put("server." + groupdb + ".chain","DBGroups,jdbc");
			nsProps.put("server." + groupdb + ".nameSpace","ou=groups," + nameSpace.getBase().getDN().toString());
			nsProps.put("server." + groupdb + ".weight","0");
			
			
			if (hasUsers) {
				nsProps.put("server." + groupdb + ".DBGroups.className","net.sourceforge.myvd.inserts.jdbc.DBGroups");
				nsProps.put("server." + groupdb + ".DBGroups.config.memberAttribute",GlobalEntries.getGlobalEntries().getConfigManager().getCfg().getGroupMemberAttribute());
				nsProps.put("server." + groupdb + ".DBGroups.config.suffix","ou=users," + nameSpace.getBase().getDN().toString());
				nsProps.put("server." + groupdb + ".DBGroups.config.rdn","uid");
			} else {
				nsProps.put("server." + groupdb + ".DBGroups.className","com.tremolosecurity.myvd.inserts.mapping.Attribute2DN");
				nsProps.put("server." + groupdb + ".DBGroups.config.attributeName",GlobalEntries.getGlobalEntries().getConfigManager().getCfg().getGroupMemberAttribute());
				nsProps.put("server." + groupdb + ".DBGroups.config.searchBase",props.get("groupUserSearchBase"));
				nsProps.put("server." + groupdb + ".DBGroups.config.searchAttribute","uid");
			}
			
			nsProps.put("server." + groupdb + ".jdbc.className","net.sourceforge.myvd.inserts.jdbc.JdbcInsert");
			nsProps.put("server." + groupdb + ".jdbc.config.driver",props.getProperty("driver"));
			nsProps.put("server." + groupdb + ".jdbc.config.url",props.getProperty("url"));
			nsProps.put("server." + groupdb + ".jdbc.config.user",props.getProperty("user"));
			nsProps.put("server." + groupdb + ".jdbc.config.password",props.getProperty("password"));
			nsProps.put("server." + groupdb + ".jdbc.config.rdn","cn");
			nsProps.put("server." + groupdb + ".jdbc.config.maxCons",props.getProperty("maxCons"));
			nsProps.put("server." + groupdb + ".jdbc.config.maxConsIdle",props.getProperty("maxConsIdle"));
			nsProps.put("server." + groupdb + ".jdbc.config.mapping",props.getProperty("group-mapping"));
			nsProps.put("server." + groupdb + ".jdbc.config.objectClass",GlobalEntries.getGlobalEntries().getConfigManager().getCfg().getGroupObjectClass());
			nsProps.put("server." + groupdb + ".jdbc.config.sql",getGroupSelect(props));
			nsProps.put("server." + groupdb + ".jdbc.config.addBaseToFilter","false");
			nsProps.put("server." + groupdb + ".jdbc.config.useSimple","true");
			if (hasValidationQuery) {
				nsProps.put("server." + groupdb + ".jdbc.config.validationQuery",validationQuery);
			}
		}
		

	}

	private String getGroupSelect(Properties props) {
		String groupTable = props.getProperty("group-table");
		String groupPrimaryKey = props.getProperty("group-primaryKey");
		String userTable = props.getProperty("user-table");
		String userPrimaryKey = props.getProperty("user-primaryKey");
		String manyToManyTable = props.getProperty("manyToManyTable");
		String manyToManyUser = props.getProperty("manyToManyTable-users");
		String manyToManyGroup = props.getProperty("manyToManyTable-groups");
		String groupMapping = props.getProperty("group-mapping");
		
		StringTokenizer toker = new StringTokenizer(groupMapping,",",false);
		
		
		
		String SQL = "SELECT ";
		
		while (toker.hasMoreTokens()) {
			 String token = toker.nextToken();
			 String fieldName = token.substring(token.indexOf('=') + 1);
			 
			 SQL += groupTable + "." + fieldName + " AS " + fieldName + ", ";
		}
		
		SQL = SQL.substring(0,SQL.lastIndexOf(','));
		
		SQL += " FROM " + groupTable + " LEFT OUTER JOIN " + manyToManyTable + " ON " + groupTable + "." + groupPrimaryKey + "=" + manyToManyTable + "." + manyToManyGroup + " LEFT OUTER JOIN " + userTable + " ON " + manyToManyTable + "." + manyToManyUser + "=" + userTable + "." + userPrimaryKey;
		
		return SQL;
	}
	
	private String getUidColumn(Properties props) {
		String mapping = props.getProperty("user-mapping");
		
		StringTokenizer toker = new StringTokenizer(mapping,",",false);
		
		
		
		while (toker.hasMoreTokens()) {
			 String token = toker.nextToken();
			 String fieldName = token.substring(token.indexOf('=') + 1);
			 String attr = token.substring(0, token.indexOf('='));
			 if (attr.equalsIgnoreCase("uid")) {
				 return fieldName;
			 }
			 
		}
		
		return null;
	}

	private String getUserSelect(Properties props) {
		String table = props.getProperty("user-table");
		String mapping = props.getProperty("user-mapping");
		
		StringTokenizer toker = new StringTokenizer(mapping,",",false);
		
		String SQL = "SELECT ";
		
		while (toker.hasMoreTokens()) {
			 String token = toker.nextToken();
			 String fieldName = token.substring(token.indexOf('=') + 1);
			 
			 SQL += fieldName + ", ";
		}
		
		SQL = SQL.substring(0,SQL.lastIndexOf(','));
		
		SQL += " FROM " + table;
		return SQL;
	}

	@Override
	public Connection getCon() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		JdbcInsert insert = (JdbcInsert) super.getChildNameSpaces().get(1).getChain().getInsert(super.getChildNameSpaces().get(1).getChain().getLength() - 1);
		return insert.getCon();
	}

	@Override
	public void returnCon(Connection con) {
		JdbcInsert insert = (JdbcInsert) super.getChildNameSpaces().get(1).getChain().getInsert(super.getChildNameSpaces().get(1).getChain().getLength() - 1);
		insert.returnCon(con);
		
	}

}
