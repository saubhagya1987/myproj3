package com.imagingserver.http.imaging.dataObjects;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Dheeraj
 * 
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ImgRequest implements Serializable {
	private final static long serialVersionUID = 1L;

	protected ImgResponse imgResponse;

	protected String imageName;
	protected String imageType;
	protected String bucketName;

	public ImgResponse getImgResponse() {
		return imgResponse;
	}

	public void setImgResponse(ImgResponse imgResponse) {
		this.imgResponse = imgResponse;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
}