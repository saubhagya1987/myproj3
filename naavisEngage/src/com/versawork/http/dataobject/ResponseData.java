package com.versawork.http.dataobject;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseData", propOrder = { "result", "description"

})
@XmlRootElement(name = "ResponseData")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ResponseData implements Serializable, ToString {
	private final static long serialVersionUID = 1L;

	@XmlElement(name = "result")
	protected int result;

	@XmlElement(name = "description")
	protected String description;
	
	@XmlElement(name = "header")
	protected String header;
	
	

	/**
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * @param header the header to set
	 */
	public void setHeader(String header) {
		this.header = header;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
		final StringBuilder buffer = new StringBuilder();
		append(null, buffer, strategy);
		return buffer.toString();
	}

	public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
		strategy.appendStart(locator, this, buffer);
		appendFields(locator, buffer, strategy);
		strategy.appendEnd(locator, this, buffer);
		return buffer;
	}

	public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {

		{
			int theResult;
			theResult = this.getResult();
			strategy.appendField(locator, this, "result", buffer, theResult);
		}
		{
			String theDescription;
			theDescription = this.getDescription();
			strategy.appendField(locator, this, "description", buffer, theDescription);
		}
		return buffer;
	}

}
