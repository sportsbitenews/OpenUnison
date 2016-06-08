/*******************************************************************************
 * Copyright 2016 Tremolo Security, Inc.
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
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.08 at 06:05:15 AM EDT 
//


package com.tremolosecurity.config.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Provides a mapping of the user based on the map
 * 				element
 * 
 * <p>Java class for mappingType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mappingType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.tremolosecurity.com/tremoloConfig}workflowChoiceTaskType">
 *       &lt;sequence>
 *         &lt;element name="map" type="{http://www.tremolosecurity.com/tremoloConfig}provisionMappingsType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="strict" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mappingType", propOrder = {
    "map"
})
public class MappingType
    extends WorkflowChoiceTaskType
{

    @XmlElement(required = true)
    protected ProvisionMappingsType map;
    @XmlAttribute(name = "strict")
    protected Boolean strict;

    /**
     * Gets the value of the map property.
     * 
     * @return
     *     possible object is
     *     {@link ProvisionMappingsType }
     *     
     */
    public ProvisionMappingsType getMap() {
        return map;
    }

    /**
     * Sets the value of the map property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProvisionMappingsType }
     *     
     */
    public void setMap(ProvisionMappingsType value) {
        this.map = value;
    }

    /**
     * Gets the value of the strict property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isStrict() {
        return strict;
    }

    /**
     * Sets the value of the strict property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setStrict(Boolean value) {
        this.strict = value;
    }

}
