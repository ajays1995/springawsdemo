package com.blog.sample.repository;

import org.springframework.data.repository.CrudRepository;

import com.blog.sample.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
