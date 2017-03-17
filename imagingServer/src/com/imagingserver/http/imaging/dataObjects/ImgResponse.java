package com.imagingserver.http.imaging.dataObjects;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Dheeraj
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ImgResponse implements Serializable {
	private final static long serialVersionUID = 1L;

	protected String responseDescription;

	protected ResponseData responseData;

	protected ServerUrlInfo serverUrlInfo;

	public ServerUrlInfo getServerUrlInfo() {
		return serverUrlInfo;
	}

	public void setServerUrlInfo(ServerUrlInfo serverUrlInfo) {
		this.serverUrlInfo = serverUrlInfo;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	public ResponseData getResponseData() {
		return responseData;
	}

	public void setResponseData(ResponseData responseData) {
		this.responseData = responseData;
	}
}
