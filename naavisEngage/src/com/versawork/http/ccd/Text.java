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
 *       &lt;sequence minOccurs="0">
 *         &lt;sequence minOccurs="0">
 *           &lt;element ref="{urn:hl7-org:v3}table"/>
 *           &lt;element ref="{urn:hl7-org:v3}content"/>
 *           &lt;element ref="{urn:hl7-org:v3}list"/>
 *           &lt;element ref="{urn:hl7-org:v3}table"/>
 *         &lt;/sequence>
 *         &lt;element ref="{urn:hl7-org:v3}list"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "content", "table" })
@XmlRootElement(name = "text")
public class Text {

	/*
	 * @XmlElementRefs({
	 * 
	 * @XmlElementRef(name = "content", namespace = "urn:hl7-org:v3", type =
	 * Content.class, required = false),
	 * 
	 * @XmlElementRef(name = "list", namespace = "urn:hl7-org:v3", type =
	 * com.versaworks.qrdaIII.jaxb.List.class, required = false),
	 * 
	 * @XmlElementRef(name = "table", namespace = "urn:hl7-org:v3", type =
	 * Table.class, required = false) })
	 * 
	 * @XmlMixed protected java.util.List<Object> content;
	 */

	@XmlElement
	protected Content content;
	/*
	 * @XmlElement protected com.versaworks.qrdaIII.data.List list;
	 */

	@XmlElement
	protected Table table;

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	/*
	 * public com.versaworks.qrdaIII.data.List getList() { return list; }
	 * 
	 * public void setList(com.versaworks.qrdaIII.data.List list) { this.list =
	 * list; }
	 */
	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	/**
	 * Gets the value of the content property.
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
	 * Objects of the following type(s) are allowed in the list {@link String }
	 * {@link Content } {@link Table } {@link com.versaworks.qrdaIII.jaxb.List }
	 * 
	 * 
	 */
	/*
	 * public java.util.List<Object> getContent() { if (content == null) {
	 * content = new ArrayList<Object>(); } return this.content; }
	 */

}
