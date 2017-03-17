package com.versawork.http.ccd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for T_effectiveTime complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="T_effectiveTime">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence minOccurs="0">
 *         &lt;element ref="{urn:hl7-org:v3}low"/>
 *         &lt;element ref="{urn:hl7-org:v3}high" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}long" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EffectiveTime", propOrder = { "low", "high", "center", "period" })
@XmlSeeAlso({ IVLTS.class })
public class EffectiveTime {

	protected Low low;
	protected High high;
	protected Center center;
	protected Period period;

	@XmlAttribute(name = "value")
	protected Long value;

	@XmlAttribute(name = "xsi:type")
	protected String xsiType;
	@XmlAttribute(name = "operator")
	protected String operator;

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public Center getCenter() {
		return center;
	}

	public void setCenter(Center center) {
		this.center = center;
	}

	public String getXsiType() {
		return xsiType;
	}

	public void setXsiType(String xsiType) {
		this.xsiType = xsiType;
	}

	/**
	 * Gets the value of the low property.
	 * 
	 * @return possible object is {@link Low }
	 * 
	 */
	public Low getLow() {
		return low;
	}

	/**
	 * Sets the value of the low property.
	 * 
	 * @param value
	 *            allowed object is {@link Low }
	 * 
	 */
	public void setLow(Low value) {
		this.low = value;
	}

	/**
	 * Gets the value of the high property.
	 * 
	 * @return possible object is {@link High }
	 * 
	 */
	public High getHigh() {
		return high;
	}

	/**
	 * Sets the value of the high property.
	 * 
	 * @param value
	 *            allowed object is {@link High }
	 * 
	 */
	public void setHigh(High value) {
		this.high = value;
	}

	/**
	 * Gets the value of the value property.
	 * 
	 * @return possible object is {@link Long }
	 * 
	 */
	public Long getValue() {
		return value;
	}

	/**
	 * Sets the value of the value property.
	 * 
	 * @param value
	 *            allowed object is {@link Long }
	 * 
	 */
	public void setValue(Long value) {
		this.value = value;
	}

}
