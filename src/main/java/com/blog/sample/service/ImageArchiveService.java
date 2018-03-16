package com.blog.sample.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.blog.sample.model.CustomerImage;

@Service
public class ImageArchiveService {
	
	@Autowired
	private AmazonS3Client s3Client;
	
	private static String S3_BUCKET_NAME = "springawsdemo";

	public CustomerImage saveImageToS3(MultipartFile multiPartFile) throws IOException {
		
		File fileToUpload = convertToFile(multiPartFile);
		String key = Instant.now().getEpochSecond() + "_" + fileToUpload.getName();
		
		/* save file */
		s3Client.putObject(new PutObjectRequest(S3_BUCKET_NAME, key, fileToUpload));
		
		/* set expiration for one month */
		
		GeneratePresignedUrlRequest generatePresignedURLRequest = new GeneratePresignedUrlRequest(S3_BUCKET_NAME, key);
		generatePresignedURLRequest.setMethod(HttpMethod.GET);
		generatePresignedURLRequest.setExpiration(DateTime.now().plusDays(2).toDate());
		
		URL url = s3Client.generatePresignedUrl(generatePresignedURLRequest);
		
		return new CustomerImage(key, url.toString());		
	}
	
	public void deleteImageFromS3(CustomerImage customerImage) {
		
		s3Client.deleteObject(new DeleteObjectRequest(S3_BUCKET_NAME, customerImage.getKey()));	
		
	}
	
	private File convertToFile(MultipartFile multipartFile) throws IOException{
		File file = new File(multipartFile.getOriginalFilename());
		file.createNewFile(); 
		FileOutputStream fos = new FileOutputStream(file); 
		fos.write(multipartFile.getBytes());
		fos.close(); 

		return file;
	}
}
