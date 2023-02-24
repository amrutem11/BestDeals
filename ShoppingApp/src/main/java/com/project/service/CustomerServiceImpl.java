package com.project.service;


import com.project.exceptions.CustomerException;
import com.project.model.Cart;
import com.project.model.Customer;
import com.project.model.User;
import com.project.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.project.exceptions.LoginException;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private LoginLogoutCustomerServiceImplementation loginLogoutCustomerServiceimplementation;



//    @Autowired
//    private CartRepo cartRepo;

    @Override
    public Customer addCustomer(Customer customer) throws CustomerException {

        Cart cart = new Cart();

        cart.setCustomer(customer);

        Customer added_customer = customerRepo.save(customer);

        if (added_customer != null) {

//            cartRepo.save(cart);

            return added_customer;

        } else {
            throw new CustomerException("OOps, Sign Up Unsuccessfull !");
        }
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
    public String removeCustomer(String key, User user) throws CustomerException, LoginException {
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

}
