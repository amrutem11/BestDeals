package com.project.service;

import java.util.Optional;

import com.project.exceptions.CurrentCustomerSessionException;
import com.project.exceptions.CustomerException;
import com.project.model.CurrentCustomerSession;
import com.project.model.Customer;
import com.project.repository.CurrentCustomerSessionRepo;
import com.project.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sheetalBisht
 *
 */

@Service
public class CurrentCustomerSessionServiceImplementation implements CurrentCustomerSessionService {

    @Autowired
    private CurrentCustomerSessionRepo currentCustomerSessionRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public CurrentCustomerSession getCurrentCustomerSession(String key) throws CurrentCustomerSessionException {

        Optional<CurrentCustomerSession> optional_CurrentCustomerSession = currentCustomerSessionRepo.findByKey(key);

        if (optional_CurrentCustomerSession.isPresent()) {

            CurrentCustomerSession currentCustomerSession = optional_CurrentCustomerSession.get();

            return currentCustomerSession;
        } else {
            throw new CurrentCustomerSessionException("Invalid key, Please Login In !");
        }

    }

    @Override
    public Customer getCustomerDetails(String key) throws CurrentCustomerSessionException, CustomerException {

        Optional<CurrentCustomerSession> optional_CurrentUserSession = currentCustomerSessionRepo.findByKey(key);

        if (optional_CurrentUserSession.isPresent()) {

            CurrentCustomerSession currentCustomerSession = optional_CurrentUserSession.get();

            Integer current_CustomerId = currentCustomerSession.getCustomerId();

            Optional<Customer> current_Customer = customerRepo.findById(current_CustomerId);

            if (current_Customer.isPresent()) {

                Customer customer = current_Customer.get();

                return customer;

            } else {
                throw new CustomerException("No Registered Customer Found !");
            }

        } else {
            throw new CurrentCustomerSessionException("Invalid key, Please Login In !");
        }

    }

    @Override
    public Integer getCurrentUserCustomerId(String key) throws CurrentCustomerSessionException {

        Optional<CurrentCustomerSession> optional_CurrentCustomerSession = currentCustomerSessionRepo.findByKey(key);

        if (optional_CurrentCustomerSession.isPresent()) {

            CurrentCustomerSession currentCustomerSession = optional_CurrentCustomerSession.get();

            Integer current_CustomerId = currentCustomerSession.getCustomerId();

            return current_CustomerId;

        } else {

            throw new CurrentCustomerSessionException("Invalid key, Please Login In !");
        }


    }

}