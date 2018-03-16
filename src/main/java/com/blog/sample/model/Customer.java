package com.blog.sample.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name="app_customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Address address;
	
	@OneToOne(cascade=CascadeType.ALL)
	private CustomerImage customerImage;	
	
	
	public Customer() {
		
	}
	
	public Customer(String firstName, String lastName, Date date, Address address, CustomerImage customerImage) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = date;
		this.address = address;
		this.customerImage = customerImage;
	}
	
	
	public long getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDate() {
		return dateOfBirth;
	}
	public void setDate(Date date) {
		this.dateOfBirth = date;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public CustomerImage getCustomerImage() {
		return customerImage;
	}
	public void setCustomerImage(CustomerImage customerImage) {
		this.customerImage = customerImage;
	}
	
	
	
	

}
