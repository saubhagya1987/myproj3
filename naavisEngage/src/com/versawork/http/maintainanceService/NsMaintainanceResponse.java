package com.versawork.http.maintainanceService;

import java.io.Serializable;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class NsMaintainanceResponse implements Serializable {
	private final static long serialVersionUID = 1L;

	protected MaintainanceResponseData maintainanceResponseData;

	public MaintainanceResponseData getMaintainanceResponseData() {
		return maintainanceResponseData;
	}

	public void setMaintainanceResponseData(MaintainanceResponseData maintainanceResponseData) {
		this.maintainanceResponseData = maintainanceResponseData;
	}

}
