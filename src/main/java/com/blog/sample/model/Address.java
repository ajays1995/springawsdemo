package com.blog.sample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="app_address")
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column(name = "street")
	private String street;
	@Column(name = "town")
	private String town;
	@Column(name = "county")
	private String country;
	@Column(name = "postcode")
	private String postCode;
	
	public Address() {
		
	}
	
	
	public Address(String street, String town, String country, String postCode) {
		super();
		this.street = street;
		this.town = town;
		this.country = country;
		this.postCode = postCode;
	}
	
	
	public long getId() {
		return id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	

}
