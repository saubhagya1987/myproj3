//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.08.13 at 04:21:51 PM IST 
//

package com.versawork.http.ccd;

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
 *       &lt;sequence>
 *         &lt;element ref="{urn:hl7-org:v3}templateId"/>
 *         &lt;element ref="{urn:hl7-org:v3}id"/>
 *         &lt;element ref="{urn:hl7-org:v3}code"/>
 *         &lt;element ref="{urn:hl7-org:v3}text"/>
 *         &lt;element ref="{urn:hl7-org:v3}statusCode"/>
 *         &lt;element ref="{urn:hl7-org:v3}effectiveTime"/>
 *         &lt;element ref="{urn:hl7-org:v3}performer"/>
 *       &lt;/sequence>
 *       &lt;attribute name="moodCode" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="EVN"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="classCode" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="PROC"/>
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
@XmlType(name = "", propOrder = { "templateId", "id", "code", "text", "statusCode", "author", "effectiveTime",
		"participant", "entryRelationship" })
@XmlRootElement(name = "procedure")
public class Procedure {

	@XmlAttribute(name = "classCode", required = true)
	protected String classCode;
	@XmlAttribute(name = "moodCode", required = true)
	protected String moodCode;

	@XmlElement(required = true)
	protected List<TemplateId> templateId;
	@XmlElement(required = true)
	protected Id id;
	@XmlElement(required = true)
	protected Code code;
	@XmlElement(required = true)
	protected String text;
	@XmlElement(required = true)
	protected StatusCode statusCode;

	@XmlElement(required = true)
	protected EffectiveTime effectiveTime;

	@XmlElement(required = true)
	protected Participant participant;

	@XmlElement(required = true)
	protected EntryRelationship entryRelationship;

	@XmlElement(required = true)
	protected Author author;

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getMoodCode() {
		return moodCode;
	}

	public void setMoodCode(String moodCode) {
		this.moodCode = moodCode;
	}

	public void setTemplateId(List<TemplateId> templateId) {
		this.templateId = templateId;
	}

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public StatusCode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(StatusCode statusCode) {
		this.statusCode = statusCode;
	}

	public EffectiveTime getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(EffectiveTime effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	public EntryRelationship getEntryRelationship() {
		return entryRelationship;
	}

	public void setEntryRelationship(EntryRelationship entryRelationship) {
		this.entryRelationship = entryRelationship;
	}

}