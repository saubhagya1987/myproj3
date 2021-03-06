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
 *         &lt;element ref="{urn:hl7-org:v3}time"/>
 *         &lt;element ref="{urn:hl7-org:v3}assignedAuthor"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "time", "assignedAuthor" })
@XmlRootElement(name = "author")
public class Author {

	@XmlElement(required = true)
	protected Time time;
	@XmlElement(required = true)
	protected AssignedAuthor assignedAuthor;

	/**
	 * Gets the value of the time property.
	 * 
	 * @return possible object is {@link Time }
	 * 
	 */
	public Time getTime() {
		return time;
	}

	/**
	 * Sets the value of the time property.
	 * 
	 * @param string
	 *            allowed object is {@link Time }
	 * 
	 */
	public void setTime(Time string) {
		this.time = string;
	}

	/**
	 * Gets the value of the assignedAuthor property.
	 * 
	 * @return possible object is {@link AssignedAuthor }
	 * 
	 */
	public AssignedAuthor getAssignedAuthor() {
		return assignedAuthor;
	}

	/**
	 * Sets the value of the assignedAuthor property.
	 * 
	 * @param value
	 *            allowed object is {@link AssignedAuthor }
	 * 
	 */
	public void setAssignedAuthor(AssignedAuthor value) {
		this.assignedAuthor = value;
	}

}
