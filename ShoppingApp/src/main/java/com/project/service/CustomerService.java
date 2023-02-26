
package com.project.service;


import com.project.exceptions.CustomerException;
import com.project.exceptions.LoginException;

import com.project.model.Customer;
import com.project.model.User;




public interface CustomerService {

	public Customer addCustomer(Customer customer) throws CustomerException;

	public Customer updateCustomer(String key, Customer customer) throws CustomerException, LoginException;

	String removeCustomer(String key, User user) throws CustomerException, LoginException;

	public Customer viewCustomer(String key, Integer customer_Id) throws CustomerException, LoginException;

}