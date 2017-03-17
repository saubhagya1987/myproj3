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
 *         &lt;element ref="{urn:hl7-org:v3}code"/>
 *       &lt;/sequence>
 *       &lt;attribute name="classCode" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="MMAT"/>
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
@XmlType(name = "", propOrder = { "code" })
@XmlRootElement(name = "manufacturedMaterial")
public class ManufacturedMaterial {

	@XmlElement(required = true)
	protected Code code;
	@XmlAttribute(name = "classCode", required = true)
	protected String classCode;

	/**
	 * Gets the value of the code property.
	 * 
	 * @return possible object is {@link Code }
	 * 
	 */
	public Code getCode() {
		return code;
	}

	/**
	 * Sets the value of the code property.
	 * 
	 * @param value
	 *            allowed object is {@link Code }
	 * 
	 */
	public void setCode(Code value) {
		this.code = value;
	}

	/**
	 * Gets the value of the classCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getClassCode() {
		return classCode;
	}

	/**
	 * Sets the value of the classCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setClassCode(String value) {
		this.classCode = value;
	}

}
