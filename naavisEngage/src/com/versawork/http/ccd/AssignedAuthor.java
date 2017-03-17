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
 *         &lt;choice>
 *           &lt;sequence>
 *             &lt;element ref="{urn:hl7-org:v3}telecom"/>
 *             &lt;element ref="{urn:hl7-org:v3}assignedAuthoringDevice"/>
 *             &lt;element ref="{urn:hl7-org:v3}representedOrganization"/>
 *           &lt;/sequence>
 *           &lt;element ref="{urn:hl7-org:v3}assignedPerson"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "id", "addr", "telecom", "assignedAuthoringDevice", "representedOrganization",
		"assignedPerson" })
@XmlRootElement(name = "assignedAuthor")
public class AssignedAuthor {

	@XmlElement(required = true)
	protected Id id;
	@XmlElement(required = true)
	protected Addr addr;
	protected Telecom telecom;
	protected AssignedAuthoringDevice assignedAuthoringDevice;
	protected RepresentedOrganization representedOrganization;
	protected AssignedPerson assignedPerson;

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
	 * Gets the value of the assignedAuthoringDevice property.
	 * 
	 * @return possible object is {@link AssignedAuthoringDevice }
	 * 
	 */
	public AssignedAuthoringDevice getAssignedAuthoringDevice() {
		return assignedAuthoringDevice;
	}

	/**
	 * Sets the value of the assignedAuthoringDevice property.
	 * 
	 * @param value
	 *            allowed object is {@link AssignedAuthoringDevice }
	 * 
	 */
	public void setAssignedAuthoringDevice(AssignedAuthoringDevice value) {
		this.assignedAuthoringDevice = value;
	}

	/**
	 * Gets the value of the representedOrganization property.
	 * 
	 * @return possible object is {@link RepresentedOrganization }
	 * 
	 */
	public RepresentedOrganization getRepresentedOrganization() {
		return representedOrganization;
	}

	/**
	 * Sets the value of the representedOrganization property.
	 * 
	 * @param value
	 *            allowed object is {@link RepresentedOrganization }
	 * 
	 */
	public void setRepresentedOrganization(RepresentedOrganization value) {
		this.representedOrganization = value;
	}

	/**
	 * Gets the value of the assignedPerson property.
	 * 
	 * @return possible object is {@link AssignedPerson }
	 * 
	 */
	public AssignedPerson getAssignedPerson() {
		return assignedPerson;
	}

	/**
	 * Sets the value of the assignedPerson property.
	 * 
	 * @param value
	 *            allowed object is {@link AssignedPerson }
	 * 
	 */
	public void setAssignedPerson(AssignedPerson value) {
		this.assignedPerson = value;
	}

}