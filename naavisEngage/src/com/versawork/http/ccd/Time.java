//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.13 at 04:21:51 PM IST 
//

package com.versawork.http.ccd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence minOccurs="0">
 *         &lt;element ref="{urn:hl7-org:v3}low"/>
 *         &lt;element ref="{urn:hl7-org:v3}high"/>
 *       &lt;/sequence>
 *       &lt;attribute name="value">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="20140624231400-0700"/>
 *             &lt;enumeration value="20140624234000-0700"/>
 *             &lt;enumeration value="20140710224302-0700"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "low", "high" })
@XmlRootElement(name = "time")
public class Time {

	protected Low low;
	protected High high;
	@XmlAttribute(name = "value")
	protected String value;

	/**
	 * Gets the value of the low property.
	 * 
	 * @return possible object is {@link Low }
	 * 
	 */
	public Low getLow() {
		return low;
	}

	/**
	 * Sets the value of the low property.
	 * 
	 * @param value
	 *            allowed object is {@link Low }
	 * 
	 */
	public void setLow(Low value) {
		this.low = value;
	}

	/**
	 * Gets the value of the high property.
	 * 
	 * @return possible object is {@link High }
	 * 
	 */
	public High getHigh() {
		return high;
	}

	/**
	 * Sets the value of the high property.
	 * 
	 * @param value
	 *            allowed object is {@link High }
	 * 
	 */
	public void setHigh(High value) {
		this.high = value;
	}

	/**
	 * Gets the value of the value property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value of the value property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
