//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.13 at 04:21:51 PM IST 
//

package com.versawork.http.ccd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *       &lt;sequence>
 *         &lt;element ref="{urn:hl7-org:v3}id"/>
 *         &lt;element ref="{urn:hl7-org:v3}addr"/>
 *         &lt;element ref="{urn:hl7-org:v3}telecom"/>
 *         &lt;element ref="{urn:hl7-org:v3}patient"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "id", "addr", "telecom", "patient" })
@XmlRootElement(name = "patientRole")
public class PatientRole {

	@XmlElement(required = true)
	protected Id id;
	@XmlElement(required = true)
	protected Addr addr;
	@XmlElement(required = true)
	protected Telecom telecom;
	@XmlElement(required = true)
	protected Patient patient;

	/**
	 * Gets the value of the id property.
	 * 
	 * @return possible object is {@link Id }
	 * 
	 */
	public Id getId() {
		return id;
	}

	/**
	 * Sets the value of the id property.
	 * 
	 * @param value
	 *            allowed object is {@link Id }
	 * 
	 */
	public void setId(Id value) {
		this.id = value;
	}

	/**
	 * Gets the value of the addr property.
	 * 
	 * @return possible object is {@link Addr }
	 * 
	 */
	public Addr getAddr() {
		return addr;
	}

	/**
	 * Sets the value of the addr property.
	 * 
	 * @param value
	 *            allowed object is {@link Addr }
	 * 
	 */
	public void setAddr(Addr value) {
		this.addr = value;
	}

	/**
	 * Gets the value of the telecom property.
	 * 
	 * @return possible object is {@link Telecom }
	 * 
	 */
	public Telecom getTelecom() {
		return telecom;
	}

	/**
	 * Sets the value of the telecom property.
	 * 
	 * @param value
	 *            allowed object is {@link Telecom }
	 * 
	 */
	public void setTelecom(Telecom value) {
		this.telecom = value;
	}

	/**
	 * Gets the value of the patient property.
	 * 
	 * @return possible object is {@link Patient }
	 * 
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * Sets the value of the patient property.
	 * 
	 * @param value
	 *            allowed object is {@link Patient }
	 * 
	 */
	public void setPatient(Patient value) {
		this.patient = value;
	}

}
