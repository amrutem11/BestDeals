package com.project.service;


import com.project.exceptions.CustomerException;
import com.project.model.Customer;
import com.project.model.Role;
import com.project.model.User;
import com.project.repository.CartRepo;
import com.project.repository.CustomerRepo;
import com.project.repository.LoginLogoutRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.project.exceptions.AdminException;
import com.project.exceptions.LoginException;
import com.project.exceptions.UserException;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private LoginLogoutRepo UserRepo;
    
    
//
//    @Autowired
//    private LoginLogoutAdminServiceImplementation loginLogoutAdminServiceimplementation;

    @Autowired
    private CartRepo cartRepo;

    @Override
    public Customer addCustomer(Customer customer) throws CustomerException {

Optional<Customer> existingCustomer= customerRepo.findByMobileNumber(customer.getMobileNumber());
		if(existingCustomer.isEmpty()) 
			throw new CustomerException("Customer Already Registered with Mobile number");
			
		User newUser=new User(customer.getMobileNumber(),customer.getPassword(),Role.Customer);
		UserRepo.save(newUser);
			return customerRepo.save(customer);
    }

    @Override
    public Customer updateCustomer(String key, Customer customer) throws CustomerException, LoginException {

        Customer validate_customer = loginLogoutCustomerServiceimplementation.validateCustomer(key);

        if (validate_customer != null) {

            return customerRepo.save(customer);

        } else {
            throw new CustomerException("Invalid Key, Please Login In !");
        }

    }

    @Override
    public String removeCustomer(String key, Integer customer_Id) throws CustomerException, LoginException {
        User validate_user = loginLogoutCustomerServiceimplementation.authenticateCustomer(user, key);

        if (validate_user != null) {

            Optional<Customer> optionalcustomer = customerRepo.findByMobileNumber(user.getId());

            if (optionalcustomer.isPresent()) {

                customerRepo.delete(optionalcustomer.get());

                return "Customer Deleted Successfully !";

            } else {
                throw new CustomerException("No Registered Customer Found With This Mobile Number : " + user.getId());
            }

        } else {
            throw new CustomerException("Invalid Login Id or Password !");
        }
    }



    @Override
    public Customer viewCustomer(String key, Integer customer_Id) throws CustomerException, LoginException {

        Customer validate_customer = loginLogoutCustomerServiceimplementation.validateCustomer(key);

        if (validate_customer != null) {

            Optional<Customer> optional_customer = customerRepo.findById(customer_Id);

            if (optional_customer.isPresent()) {

                return optional_customer.get();
            } else {
                throw new CustomerException("No Customer Found With The Customer Id : " + customer_Id);
            }

        } else {
            throw new CustomerException("Invalid Key, Please Login In !");
        }

    }

    @Override
    public List<Customer> viewAllCustomers(String key) throws AdminException, CustomerException, LoginException {

        Admin validate_admin = loginLogoutAdminServiceimplementation.validateAdmin(key);

        if (validate_admin != null) {

            List<Customer> listofcustomers = customerRepo.findAll();

            if (listofcustomers.isEmpty()) {
                throw new CustomerException("No Customers Available in the Database!");
            } else {
                return listofcustomers;
            }

        } else {
            throw new AdminException("Invalid Key, Please Login In as Admin!");
        }

    }}

