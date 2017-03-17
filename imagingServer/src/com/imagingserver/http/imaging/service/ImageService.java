package com.imagingserver.http.imaging.service;

import java.io.Serializable;
import com.imagingserver.http.imaging.dataObjects.ImgRequest;
import com.imagingserver.http.imaging.dataObjects.ImgResponse;

/* applicationContext.xml should be place out of the jar file while making the .jar file of imaging server */
public interface ImageService extends Serializable {

	ImgResponse uploadImage(ImgRequest imgRequest) throws Exception;

	ImgResponse downloadImage(String imgName, String imageType) throws Exception;

}
