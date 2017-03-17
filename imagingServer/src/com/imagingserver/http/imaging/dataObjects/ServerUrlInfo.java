package com.imagingserver.http.imaging.dataObjects;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerUrlInfo implements Serializable {
	private final static long serialVersionUID = 1L;

	protected String ImagingURL;

	public String getImagingURL() {
		return ImagingURL;
	}

	public void setImagingURL(String imagingURL) {
		ImagingURL = imagingURL;
	}

	void display() {
		// System.out.println("s3ImagingURL: "+ImagingURL);
	}
}
