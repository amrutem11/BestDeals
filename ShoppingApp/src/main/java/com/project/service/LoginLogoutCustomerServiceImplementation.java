/**
 *
 */
package com.project.service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.project.exceptions.CustomerException;
import com.project.exceptions.LoginException;
import com.project.exceptions.LogoutException;
import com.project.exceptions.UserException;
import com.project.model.CurrentCustomerSession;
import com.project.model.Customer;
import com.project.model.User;
import com.project.repository.CurrentCustomerSessionRepo;
import com.project.repository.CustomerRepo;
import com.project.service.LoginLogoutCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bytebuddy.utility.RandomString;

/**
 * @author sheetalbisht
 *
 */

@Service
public class LoginLogoutCustomerServiceImplementation implements LoginLogoutCustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private CurrentCustomerSessionRepo currentCustomerSessionRepo;


    @Override
    public CurrentCustomerSession loginCustomer(User user) throws LoginException, CustomerException {
        if ("Customer".equals(user.getRole())) {

            Optional<Customer> optional_customer = customerRepo.findByMobileNumber(user.getId());

            if (optional_customer.isPresent()) {

                Customer customer = optional_customer.get();

                Optional<CurrentCustomerSession> optional_CurrentUserSession = currentCustomerSessionRepo
                        .findByCustomerId(customer.getCustomerId());

                if (optional_CurrentUserSession.isPresent()) {

                    throw new LoginException(
                            "User Already Logged In With This Customer Id : " + customer.getCustomerId());
                } else {

                    if (user.getId().equals(customer.getMobileNumber())
                            && user.getPassword().equals(customer.getPassword())) {

                        CurrentCustomerSession currentCustomerSession = new CurrentCustomerSession();

                        String key = RandomString.make(6);

                        currentCustomerSession.setCustomerId(customer.getCustomerId());
                        currentCustomerSession.setKey(key);
                        currentCustomerSession.setLocalDateTime(LocalDateTime.now());

                        return currentCustomerSessionRepo.save(currentCustomerSession);

                    } else {
                        throw new LoginException("Invalid User_Id or Password");
                    }
                }

            } else {
                throw new CustomerException("No Registered Customer Found With This User_Id : " + user.getId());
            }

        } else {

            throw new LoginException("Please, Select Customer as Role to Login !");
        }

    }

    @Override
    public String logoutCustomer(String key) throws LogoutException {
        Optional<CurrentCustomerSession> currentCustomerSession = currentCustomerSessionRepo.findByKey(key);

        if (currentCustomerSession.isPresent()) {

            currentCustomerSessionRepo.delete(currentCustomerSession.get());

            return "Logged Out Successfully !";

        } else {
            throw new LogoutException("No User Logged In !");
        }
    }

    @Override
    public User authenticateCustomer(User user, String key) throws UserException, LoginException, CustomerException {
        Optional<CurrentCustomerSession> optional_currentCustomerSession = currentCustomerSessionRepo.findByKey(key);

        if (optional_currentCustomerSession.isPresent()) {

            CurrentCustomerSession currentCustomerSession = optional_currentCustomerSession.get();

            Optional<Customer> optional_customer = customerRepo.findById(currentCustomerSession.getCustomerId());

            if (optional_customer.isPresent()) {

                Customer customer = optional_customer.get();

                if (customer.getMobileNumber().equals(user.getId())
                        && customer.getPassword().equals(user.getPassword())) {

                    return user;
                } else {
                    throw new UserException("Invalid UserId or Password");
                }

            } else {
                throw new CustomerException(
                        "No Customer Found with this Customer Id : " + currentCustomerSession.getCustomerId());
            }

        } else {
            throw new LoginException("Invalid Key, Please Login In !");
        }
    }

    @Override
    public Customer validateCustomer(String key) throws LoginException, CustomerException {
        Optional<CurrentCustomerSession> optional_currentCustomerSession = currentCustomerSessionRepo.findByKey(key);

        if (optional_currentCustomerSession.isPresent()) {

            CurrentCustomerSession currentCustomerSession = optional_currentCustomerSession.get();

            Optional<Customer> optional_customer = customerRepo.findById(currentCustomerSession.getCustomerId());

            if (optional_customer.isPresent()) {

                Customer customer = optional_customer.get();

                return customer;

            } else {
                throw new CustomerException(
                        "No Customer Found with this Customer Id : " + currentCustomerSession.getCustomerId());
            }

        } else {
            throw new LoginException("Invalid Key, Please Login In !");
        }

    }
}