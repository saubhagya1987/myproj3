package com.imagingserver.http.imaging.serviceImpl;

import java.io.File;
import java.net.URL;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.imagingserver.http.imaging.dataObjects.ImgRequest;
import com.imagingserver.http.imaging.dataObjects.ImgResponse;
import com.imagingserver.http.imaging.dataObjects.ResponseData;
import com.imagingserver.http.imaging.dataObjects.ServerUrlInfo;
import com.imagingserver.http.imaging.service.ImageService;

@Service
public class S3ImageServiceImpl implements ImageService {
	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	@Autowired
	MessageSource messageSource;

	public ImgResponse uploadImage(ImgRequest imgRequest) throws Exception {
		try {
			String ACCESS_KEY_ID = messageSource.getMessage("ACCESS.KEY.ID", null, Locale.getDefault());
			String SECRET_ACCESS_KEY = messageSource.getMessage("SECRET.ACCESS.KEY", null, Locale.getDefault());
			AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY);

			AmazonS3 s3client = new AmazonS3Client(credentials);
			ImgResponse imgResponse = new ImgResponse();
			ResponseData responseData = new ResponseData();
			ServerUrlInfo serverUrlInfo = new ServerUrlInfo();

			String bucketName = imgRequest.getBucketName();
			s3client.createBucket(bucketName);

			String imagePath = imgResponse.getServerUrlInfo().getImagingURL();
			String fileName = imgRequest.getImageName() + "." + imgRequest.getImageType();

			File file = new File(imagePath);

			s3client.putObject(new PutObjectRequest(bucketName, fileName, file));

			GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, fileName);
			s3client.setObjectAcl(bucketName, fileName, CannedAccessControlList.PublicRead);
			URL uri = s3client.generatePresignedUrl(request);
			// System.out.println(uri.toString().split("\\?")[0]);

			responseData.setResult(0);
			responseData.setDescription("Success");
			serverUrlInfo.setImagingURL(uri.toString().split("\\?")[0]);
			imgResponse.setResponseData(responseData);
			imgResponse.setServerUrlInfo(serverUrlInfo);
			return imgResponse;
		} catch (Exception e) {
			throw new Exception("Exception while uploading image to S3", e);
		}
	}

	public ImgResponse downloadImage(String imgName, String imageType) throws Exception {
		try {
			String IMAGE_URL = messageSource.getMessage("S3.IMAGE.URL", null, Locale.getDefault());
			ImgResponse imgResponse = new ImgResponse();
			ServerUrlInfo serverUrlInfo = new ServerUrlInfo();

			serverUrlInfo.setImagingURL(IMAGE_URL + File.separator + imgName + "." + imageType);

			// System.out.println("URL for s3: " + getS3_IMAGE_URL()+ imgName +
			// "." + getIMAGE_TYPE());

			imgResponse.setServerUrlInfo(serverUrlInfo);

			return imgResponse;
		} catch (Exception e) {
			throw new Exception("Exception while downloading image from S3", e);
		}
	}
}