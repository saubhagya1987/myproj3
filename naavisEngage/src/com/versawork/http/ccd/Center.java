package com.versawork.http.ccd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "high")
public class Center {

	@XmlAttribute(name = "value")
	protected Long value;
	@XmlAttribute(name = "nullFlavor")
	protected String nullFlavor;

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