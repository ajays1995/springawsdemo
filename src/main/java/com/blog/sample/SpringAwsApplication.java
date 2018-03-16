package com.blog.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;

@SpringBootApplication
public class SpringAwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAwsApplication.class, args);
	}
	
	
	@SuppressWarnings("deprecation")
	@Bean(name="s3Client")
	public AmazonS3Client getAmazonS3Client()
	{
		AWSCredentials credentials = new BasicAWSCredentials("AKIAJ2VIANJ5ITJJCTOQ", "1cUlLQd7LJrqNqPzRifAIHbQ3EucOzSzaSbpwjvZ");

		return new AmazonS3Client(credentials);
	}
}
