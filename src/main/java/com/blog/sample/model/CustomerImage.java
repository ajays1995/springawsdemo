package com.blog.sample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="app_customer_image")
public class CustomerImage {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column(name = "s3_key")
	private String key;
	@Column(name = "url")
	private String url;
	
	
	public CustomerImage() {
		
	}	
	
	public CustomerImage(String key, String url) {
		super();
		this.key = key;
		this.url = url;
	}
	public long getId() {
		return id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
