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
 *       &lt;attribute name="value">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
 *             &lt;enumeration value="20140623"/>
 *             &lt;enumeration value="20140624"/>
 *             &lt;enumeration value="201406241626"/>
 *             &lt;enumeration value="201406241627"/>
 *             &lt;enumeration value="20140627"/>
 *             &lt;enumeration value="20140702"/>
 *             &lt;enumeration value="20140710"/>
 *             &lt;enumeration value="201407100000"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="nullFlavor">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="UNK"/>
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
@XmlType(name = "")
@XmlRootElement(name = "low")
public class Low {

	@XmlAttribute(name = "value")
	protected Long value;
	@XmlAttribute(name = "unit")
	protected String unit;
	@XmlAttribute(name = "nullFlavor")
	protected String nullFlavor;

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * Gets the value of the value property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getValue() {
		return value;
	}

	/**
	 * Sets the value of the value property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setValue(Long value) {
		this.value = value;
	}

	/**
	 * Gets the value of the nullFlavor property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getNullFlavor() {
		return nullFlavor;
	}

	/**
	 * Sets the value of the nullFlavor property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setNullFlavor(String value) {
		this.nullFlavor = value;
	}

}