package com.imagingserver.http.imaging.serviceImpl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.imagingserver.http.imaging.dataObjects.ImgRequest;
import com.imagingserver.http.imaging.dataObjects.ImgResponse;
import com.imagingserver.http.imaging.dataObjects.ResponseData;
import com.imagingserver.http.imaging.dataObjects.ServerUrlInfo;
import com.imagingserver.http.imaging.service.ImageService;

@Service
public class LocalImageServiceImpl implements ImageService {
	protected String LOCAL_IMAGE_URL;
	protected String IMAGE_NAME;
	protected String IMAGE_TYPE;
	protected String WRITE_FILE_TO_PATH;

	public String getLOCAL_IMAGE_URL() {
		return LOCAL_IMAGE_URL;
	}

	public void setLOCAL_IMAGE_URL(String lOCAL_IMAGE_URL) {
		LOCAL_IMAGE_URL = lOCAL_IMAGE_URL;
	}

	public String getIMAGE_NAME() {
		return IMAGE_NAME;
	}

	public void setIMAGE_NAME(String iMAGE_NAME) {
		IMAGE_NAME = iMAGE_NAME;
	}

	public String getIMAGE_TYPE() {
		return IMAGE_TYPE;
	}

	public void setIMAGE_TYPE(String iMAGE_TYPE) {
		IMAGE_TYPE = iMAGE_TYPE;
	}

	public String getWRITE_FILE_TO_PATH() {
		return WRITE_FILE_TO_PATH;
	}

	public void setWRITE_FILE_TO_PATH(String wRITE_FILE_TO_PATH) {
		WRITE_FILE_TO_PATH = wRITE_FILE_TO_PATH;
	}

	public ImgResponse uploadImage(ImgRequest imgRequest) throws Exception {
		BufferedImage image = null;
		try {
			ImgResponse imgResponse = new ImgResponse();
			ResponseData responseData = new ResponseData();

			File url = new File(getLOCAL_IMAGE_URL());
			image = ImageIO.read(url);
			ImageIO.write(image, "jpg", new File(getWRITE_FILE_TO_PATH()));

			responseData.setResult(0);
			responseData.setDescription("Success");
			imgResponse.setResponseData(responseData);

			return imgResponse;

		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println("Done");
		return null;
	}

	@SuppressWarnings("null")
	public ImgResponse downloadImage(String imgName, String imageType) throws Exception {
		ImgResponse imgResponse = new ImgResponse();
		try {
			ResponseData responseData = new ResponseData();
			ServerUrlInfo serverUrlInfo = new ServerUrlInfo();

			if (imgName != null || imgName.isEmpty()) {
				serverUrlInfo.setImagingURL(getLOCAL_IMAGE_URL() + imgName + "." + imageType);
				// System.out.println("Get local url: "+getLOCAL_IMAGE_URL()+
				// imgName + "."+getIMAGE_TYPE());
			} else {
				serverUrlInfo.setImagingURL(getLOCAL_IMAGE_URL());
			}
			// LOGGER.debug("URL downloadImage : \n"+
			// imgResponse.getServerUrlInfo().getLocalImagingURL());
			responseData.setResult(0);
			responseData.setDescription("Success");
			imgResponse.setResponseData(responseData);
			imgResponse.setServerUrlInfo(serverUrlInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imgResponse;
	}
}