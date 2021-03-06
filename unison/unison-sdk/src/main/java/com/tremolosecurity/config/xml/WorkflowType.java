/*******************************************************************************
 * Copyright 2017 Tremolo Security, Inc.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.05.13 at 06:31:38 AM EDT 
//


package com.tremolosecurity.config.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A workflow is a series of tasks and decisions to
 * 				update downstream identity stores
 * 
 * <p>Java class for workflowType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="workflowType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dynamicConfiguration" type="{http://www.tremolosecurity.com/tremoloConfig}dynamicWorkflowType" minOccurs="0"/>
 *         &lt;element name="tasks" type="{http://www.tremolosecurity.com/tremoloConfig}workflowTasksType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="inList" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="orgid" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "workflowType", propOrder = {
    "dynamicConfiguration",
    "tasks"
})
public class WorkflowType {

    protected DynamicWorkflowType dynamicConfiguration;
    @XmlElement(required = true)
    protected WorkflowTasksType tasks;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "label")
    protected String label;
    @XmlAttribute(name = "description")
    protected String description;
    @XmlAttribute(name = "inList")
    protected Boolean inList;
    @XmlAttribute(name = "orgid")
    protected String orgid;

    /**
     * Gets the value of the dynamicConfiguration property.
     * 
     * @return
     *     possible object is
     *     {@link DynamicWorkflowType }
     *     
     */
    public DynamicWorkflowType getDynamicConfiguration() {
        return dynamicConfiguration;
    }

    /**
     * Sets the value of the dynamicConfiguration property.
     * 
     * @param value
     *     allowed object is
     *     {@link DynamicWorkflowType }
     *     
     */
    public void setDynamicConfiguration(DynamicWorkflowType value) {
        this.dynamicConfiguration = value;
    }

    /**
     * Gets the value of the tasks property.
     * 
     * @return
     *     possible object is
     *     {@link WorkflowTasksType }
     *     
     */
    public WorkflowTasksType getTasks() {
        return tasks;
    }

    /**
     * Sets the value of the tasks property.
     * 
     * @param value
     *     allowed object is
     *     {@link WorkflowTasksType }
     *     
     */
    public void setTasks(WorkflowTasksType value) {
        this.tasks = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the label property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the value of the label property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabel(String value) {
        this.label = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the inList property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isInList() {
        return inList;
    }

    /**
     * Sets the value of the inList property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInList(Boolean value) {
        this.inList = value;
    }

    /**
     * Gets the value of the orgid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgid() {
        return orgid;
    }

    /**
     * Sets the value of the orgid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgid(String value) {
        this.orgid = value;
    }

}
