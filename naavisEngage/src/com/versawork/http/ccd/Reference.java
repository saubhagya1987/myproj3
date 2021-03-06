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
 *       &lt;attribute name="value" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="#allergy-1"/>
 *             &lt;enumeration value="#allergy-2"/>
 *             &lt;enumeration value="#allergy-3"/>
 *             &lt;enumeration value="#m-problem-1"/>
 *             &lt;enumeration value="#m-problem-2"/>
 *             &lt;enumeration value="#medication-1"/>
 *             &lt;enumeration value="#medication-2"/>
 *             &lt;enumeration value="#medication-3"/>
 *             &lt;enumeration value="#procedure-1"/>
 *             &lt;enumeration value="#procedure-2"/>
 *             &lt;enumeration value="#reaction-1"/>
 *             &lt;enumeration value="#reaction-2"/>
 *             &lt;enumeration value="#reaction-3"/>
 *             &lt;enumeration value="#rxinst-1"/>
 *             &lt;enumeration value="#rxinst-2"/>
 *             &lt;enumeration value="#rxinst-3"/>
 *             &lt;enumeration value="#rxsig-1"/>
 *             &lt;enumeration value="#rxsig-2"/>
 *             &lt;enumeration value="#rxsig-3"/>
 *             &lt;enumeration value="#severity-1"/>
 *             &lt;enumeration value="#severity-2"/>
 *             &lt;enumeration value="#severity-3"/>
 *             &lt;enumeration value="algstatus-1"/>
 *             &lt;enumeration value="algstatus-2"/>
 *             &lt;enumeration value="algstatus-3"/>
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
@XmlRootElement(name = "reference")
public class Reference {

	@XmlAttribute(name = "value", required = true)
	protected String value;

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
