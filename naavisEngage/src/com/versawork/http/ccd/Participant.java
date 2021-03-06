//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.13 at 04:21:51 PM IST 
//

package com.versawork.http.ccd;

import java.util.ArrayList;
import java.util.List;
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
 *       &lt;choice>
 *         &lt;sequence>
 *           &lt;element ref="{urn:hl7-org:v3}templateId"/>
 *           &lt;choice>
 *             &lt;sequence>
 *               &lt;element ref="{urn:hl7-org:v3}time"/>
 *               &lt;element ref="{urn:hl7-org:v3}participantRole"/>
 *             &lt;/sequence>
 *             &lt;element ref="{urn:hl7-org:v3}associatedEntity"/>
 *           &lt;/choice>
 *         &lt;/sequence>
 *         &lt;element ref="{urn:hl7-org:v3}participantRole"/>
 *       &lt;/choice>
 *       &lt;attribute name="typeCode" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="COV"/>
 *             &lt;enumeration value="CSM"/>
 *             &lt;enumeration value="HLD"/>
 *             &lt;enumeration value="IND"/>
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
@XmlType(name = "", propOrder = { "content", "templateId", "participantRole" })
@XmlRootElement(name = "participant")
public class Participant {

	/*
	 * @XmlElementRefs({
	 * 
	 * @XmlElementRef(name = "associatedEntity", namespace = "urn:hl7-org:v3",
	 * type = AssociatedEntity.class),
	 * 
	 * @XmlElementRef(name = "participantRole", namespace = "urn:hl7-org:v3",
	 * type = ParticipantRole.class),
	 * 
	 * @XmlElementRef(name = "time", namespace = "urn:hl7-org:v3", type =
	 * Time.class),
	 * 
	 * @XmlElementRef(name = "templateId", namespace = "urn:hl7-org:v3", type =
	 * TemplateId.class) })
	 */
	@XmlElement(required = true)
	protected TemplateId templateId;
	protected List<Object> content;
	@XmlAttribute(name = "typeCode", required = true)
	protected String typeCode;
	@XmlElement(required = true)
	protected ParticipantRole participantRole;

	/**
	 * Gets the rest of the content model.
	 * 
	 * <p>
	 * You are getting this "catch-all" property because of the following
	 * reason: The field name "ParticipantRole" is used by two different parts
	 * of a schema. See: line 1041 of
	 * file:/C:/Users/Nitin/Workspace/Versaworks_Phase4
	 * /JAXB/phase2_xmlSchema.xsd line 1036 of
	 * file:/C:/Users/Nitin/Workspace/Versaworks_Phase4
	 * /JAXB/phase2_xmlSchema.xsd
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
	 * {@link TemplateId } {@link AssociatedEntity } {@link Time }
	 * {@link ParticipantRole }
	 * 
	 * 
	 */

	public List<Object> getContent() {
		if (content == null) {
			content = new ArrayList<Object>();
		}
		return this.content;
	}

	public TemplateId getTemplateId() {
		return templateId;
	}

	public void setTemplateId(TemplateId templateId) {
		this.templateId = templateId;
	}

	/**
	 * Gets the value of the typeCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTypeCode() {
		return typeCode;
	}

	/**
	 * Sets the value of the typeCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTypeCode(String value) {
		this.typeCode = value;
	}

	public ParticipantRole getParticipantRole() {
		return participantRole;
	}

	public void setParticipantRole(ParticipantRole participantRole) {
		this.participantRole = participantRole;
	}

}
