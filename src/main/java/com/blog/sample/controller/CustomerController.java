package com.blog.sample.controller;

import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.sample.model.Address;
import com.blog.sample.model.Customer;
import com.blog.sample.model.CustomerImage;
import com.blog.sample.repository.CustomerRepository;
import com.blog.sample.service.ImageArchiveService;

@RestController
@RequestMapping("/api")
public class CustomerController {
	
	protected static Logger logger = LoggerFactory.getLogger(CustomerController.class.getName());
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ImageArchiveService imageArchiveService;
	
	@RequestMapping(value="/customer/{customerId}",method=RequestMethod.GET)
	public ResponseEntity<Customer> getCustomer(@PathVariable Long customerId){
		
		Customer customer = customerRepository.findOne(customerId);
		
		if(null == customer)
		{
			logger.error("Customer with Customer ID "+ customerId+"  is not found ");
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(customer);
		
	}
	
	@RequestMapping(value="/customer",method=RequestMethod.GET)
	public ResponseEntity<Iterable<Customer>> getCustomers(){
		
		Iterable<Customer> customers = customerRepository.findAll();
		
		return ResponseEntity.ok(customers);
	}
	
	@RequestMapping(value="/customer", method=RequestMethod.POST)
	public ResponseEntity<Customer> createCustomer(
			@RequestParam(value="firstName") String firstName,
			@RequestParam(value="lastName")String lastName,
			@RequestParam(value="dateOfBirth") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfBirth,
			@RequestParam(value="street") String street,
			@RequestParam(value="town") String town,
			@RequestParam(value="country") String country,
			@RequestParam(value="postCode") String postCode,
			@RequestParam(value="image") MultipartFile image){
		
		Address address = new Address(street, town, country, postCode);
		
		CustomerImage customerImage;
		try {
			customerImage = imageArchiveService.saveImageToS3(image);
			
			Customer customer = new Customer(firstName, lastName, dateOfBirth, address, customerImage);
			
			customerRepository.save(customer);
			
			return ResponseEntity.ok(customer);
			
		} catch (IOException e) {
			System.out.println("##################333Exception while saving in S3################");
			logger.error("Exception while saving the image in S3");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@RequestMapping(value="/customer/{customerId}", method=RequestMethod.DELETE)
	public ResponseEntity<String> removeCustomer(@PathVariable Long customerId ) {
		
		boolean exists = customerRepository.exists(customerId);
		if(exists){
			Customer customer = customerRepository.findOne(customerId);
			imageArchiveService.deleteImageFromS3(customer.getCustomerImage());
			customerRepository.delete(customerId);
			return ResponseEntity.ok().build();
		}		
		return ResponseEntity.notFound().build();
	}
}
