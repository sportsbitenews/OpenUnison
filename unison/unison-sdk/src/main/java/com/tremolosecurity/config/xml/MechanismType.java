/*******************************************************************************
 * Copyright 2015 Tremolo Security, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.12 at 09:01:40 AM EDT 
//


package com.tremolosecurity.config.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for mechanismType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mechanismType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="uri" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="className" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="init" type="{http://www.tremolosecurity.com/tremoloConfig}configType"/>
 *         &lt;element name="params" type="{http://www.tremolosecurity.com/tremoloConfig}paramListType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mechanismType", propOrder = {
    "uri",
    "className",
    "init",
    "params"
})
public class MechanismType {

    @XmlElement(required = true)
    protected String uri;
    @XmlElement(required = true)
    protected String className;
    @XmlElement(required = true)
    protected ConfigType init;
    @XmlElement(required = true)
    protected ParamListType params;
    @XmlAttribute(name = "name")
    protected String name;

    /**
     * Gets the value of the uri property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUri() {
        return uri;
    }

    /**
     * Sets the value of the uri property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUri(String value) {
        this.uri = value;
    }

    /**
     * Gets the value of the className property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassName() {
        return className;
    }

    /**
     * Sets the value of the className property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassName(String value) {
        this.className = value;
    }

    /**
     * Gets the value of the init property.
     * 
     * @return
     *     possible object is
     *     {@link ConfigType }
     *     
     */
    public ConfigType getInit() {
        return init;
    }

    /**
     * Sets the value of the init property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfigType }
     *     
     */
    public void setInit(ConfigType value) {
        this.init = value;
    }

    /**
     * Gets the value of the params property.
     * 
     * @return
     *     possible object is
     *     {@link ParamListType }
     *     
     */
    public ParamListType getParams() {
        return params;
    }

    /**
     * Sets the value of the params property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParamListType }
     *     
     */
    public void setParams(ParamListType value) {
        this.params = value;
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

}
