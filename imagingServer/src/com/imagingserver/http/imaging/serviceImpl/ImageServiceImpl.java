package com.imagingserver.http.imaging.serviceImpl;

import com.imagingserver.http.imaging.dataObjects.ImgRequest;
import com.imagingserver.http.imaging.dataObjects.ImgResponse;
import com.imagingserver.http.imaging.service.ImageService;

public class ImageServiceImpl {

	private ImageService imageService;

	public ImageService getImageService() {
		return imageService;
	}

	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
	}

	public ImgResponse upload(ImgRequest imgRequest) throws Exception {
		return imageService.uploadImage(imgRequest);
	}

	public ImgResponse download(String name, String imageType) throws Exception {
		return imageService.downloadImage(name, imageType);
	}
}