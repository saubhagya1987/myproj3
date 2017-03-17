package com.versawork.http.ccd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "value")
public class Value {

	protected ObservationRangeLow low;

	protected ObservationRangeHigh high;

	@XmlAttribute(name = "xsi:type")
	protected String xsiType;

	@XmlAttribute(name = "code")
	protected String code;

	@XmlAttribute(name = "displayName")
	protected String displayName;

	@XmlAttribute(name = "codeSystem")
	protected String codeSystem;

	@XmlAttribute(name = "codeSystemName")
	protected String codeSystemName;

	@XmlAttribute(name = "value")
	protected String value;

	@XmlAttribute(name = "unit")
	protected String unit;

	@XmlAttribute(name = "nullFlavor")
	protected String nullFlavor;

	public ObservationRangeLow getLow() {
		return low;
	}

	public void setLow(ObservationRangeLow low) {
		this.low = low;
	}

	public ObservationRangeHigh getHigh() {
		return high;
	}

	public void setHigh(ObservationRangeHigh high) {
		this.high = high;
	}

	public String getCodeSystemName() {
		return codeSystemName;
	}

	public String getNullFlavor() {
		return nullFlavor;
	}

	public void setNullFlavor(String nullFlavor) {
		this.nullFlavor = nullFlavor;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public void setCodeSystemName(String codeSystemName) {
		this.codeSystemName = codeSystemName;
	}

	public String getXsiType() {
		return xsiType;
	}

	public void setXsiType(String xsiType) {
		this.xsiType = xsiType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeSystem() {
		return codeSystem;
	}

	public void setCodeSystem(String codeSystem) {
		this.codeSystem = codeSystem;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
