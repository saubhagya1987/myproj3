//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.13 at 04:21:51 PM IST 
//

package com.versawork.http.ccd;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
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
 *       &lt;choice>
 *         &lt;sequence>
 *           &lt;element ref="{urn:hl7-org:v3}th" maxOccurs="unbounded"/>
 *           &lt;element ref="{urn:hl7-org:v3}td" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;element ref="{urn:hl7-org:v3}td" maxOccurs="unbounded"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "content", "td", "th"

})
@XmlRootElement(name = "tr")
public class Tr {

	@XmlElementRefs({ @XmlElementRef(name = "td", namespace = "urn:hl7-org:v3", type = Td.class),
			@XmlElementRef(name = "th", namespace = "urn:hl7-org:v3", type = JAXBElement.class) })
	protected List<Object> content;
	@XmlElement
	protected List<Td> td;
	@XmlElement
	protected List<Th> th;

	/**
	 * Gets the rest of the content model.
	 * 
	 * <p>
	 * You are getting this "catch-all" property because of the following
	 * reason: The field name "Td" is used by two different parts of a schema.
	 * See: line 167 of
	 * file:/C:/Users/Nitin/Workspace/Versaworks_Phase4/JAXB/phase2_xmlSchema
	 * .xsd line 165 of
	 * file:/C:/Users/Nitin/Workspace/Versaworks_Phase4/JAXB/phase2_xmlSchema
	 * .xsd
	 * <p>
	 * To get rid of this property, apply a property customization to one of
	 * both of the following declarations to change their names: Gets the value
	 * of the content property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the content property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getContent().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link JAXBElement }{@code <}{@link String }{@code >} {@link Td }
	 * 
	 * 
	 */
	public List<Object> getContent() {
		if (content == null) {
			content = new ArrayList<Object>();
		}
		return this.content;
	}

	public List<Td> getTd() {
		if (td == null) {
			td = new ArrayList<Td>();
		}
		return td;
	}

	public void setTd(List<Td> td) {
		this.td = td;
	}

	public List<Th> getTh() {
		if (th == null) {
			th = new ArrayList<Th>();
		}
		return th;
	}

	public void setTh(List<Th> th) {
		this.th = th;
	}

}
