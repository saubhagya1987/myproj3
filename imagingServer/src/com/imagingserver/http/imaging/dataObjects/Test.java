package com.imagingserver.http.imaging.dataObjects;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.*;

import com.google.gson.Gson;
import com.imagingserver.http.imaging.dataObjects.ImgResponse;

public class Test {
	public static void main(String[] args) {
		Gson gson = new Gson();

		try {
			String imageURL;

			String imgName = "test1";
			Resource r = new ClassPathResource("applicationContext.xml");
			BeanFactory factory = new XmlBeanFactory(r);
			com.imagingserver.http.imaging.serviceImpl.ImageServiceImpl imageService = (com.imagingserver.http.imaging.serviceImpl.ImageServiceImpl) factory
					.getBean("imgService");
			// ImgResponse imgResponse = imageService.download(imgName);

			// String responseJson = gson.toJson(imgResponse);
			// imgResponse = gson.fromJson(responseJson, ImgResponse.class);

			// System.out.println("imageURL : "+imgResponse.getServerUrlInfo().getImagingURL()
			// );

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}