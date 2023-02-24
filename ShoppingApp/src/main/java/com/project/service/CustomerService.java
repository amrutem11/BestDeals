
package com.project.service;

import com.project.exceptions.AdminException;
import com.project.exceptions.CustomerException;
import com.project.exceptions.LoginException;
import com.project.exceptions.UserException;
import com.project.model.Customer;

import java.util.List;




public interface CustomerService {

	public Customer addCustomer(Customer customer) throws CustomerException;

	public Customer updateCustomer(String key, Customer customer) throws CustomerException, LoginException;

	public String removeCustomer(String key, Integer customer_Id) throws CustomerException, LoginException;

//	String removeCustomer(String key, User user) throws CustomerException, LoginException, UserException;

	public Customer viewCustomer(String key, Integer customer_Id) throws CustomerException, LoginException;

	// Check for Admin Role
	public List<Customer> viewAllCustomers(String key) throws AdminException, LoginException, CustomerException;

}