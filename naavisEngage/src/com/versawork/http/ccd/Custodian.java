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
 *         &lt;element ref="{urn:hl7-org:v3}assignedCustodian"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "assignedCustodian" })
@XmlRootElement(name = "custodian")
public class Custodian {

	@XmlElement(required = true)
	protected AssignedCustodian assignedCustodian;

	/**
	 * Gets the value of the assignedCustodian property.
	 * 
	 * @return possible object is {@link AssignedCustodian }
	 * 
	 */
	public AssignedCustodian getAssignedCustodian() {
		return assignedCustodian;
	}

	/**
	 * Sets the value of the assignedCustodian property.
	 * 
	 * @param value
	 *            allowed object is {@link AssignedCustodian }
	 * 
	 */
	public void setAssignedCustodian(AssignedCustodian value) {
		this.assignedCustodian = value;
	}

}
