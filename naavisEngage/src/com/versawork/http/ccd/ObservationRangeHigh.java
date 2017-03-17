package com.versawork.http.ccd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "high")
public class ObservationRangeHigh {

	//
	// This file was generated by the JavaTM Architecture for XML Binding(JAXB)
	// Reference Implementation, v2.2.4-2
	// See <a
	// href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
	// Any modifications to this file will be lost upon recompilation of the
	// source schema.
	// Generated on: 2014.08.13 at 04:21:51 PM IST
	//

	@XmlAttribute(name = "value")
	protected Double value;
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
	public Double getValue() {
		return value;
	}

	/**
	 * Sets the value of the value property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setValue(Double value) {
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