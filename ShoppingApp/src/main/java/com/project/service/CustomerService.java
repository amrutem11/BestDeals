package com.project.service;

import java.util.List;

import com.project.model.Customer;
import com.project.model.User;

import jakarta.validation.Valid;

public interface CustomerService {

	Customer addCustomer(@Valid Customer customer);

	Customer updateCustomer(String key, @Valid Customer customer);

	Customer viewCustomer(String key, @Valid Integer customer_Id);

	List<Customer> viewAllCustomers(String key);

	String removeCustomer(String key, @Valid User user);

}
