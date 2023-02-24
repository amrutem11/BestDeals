package com.project.service;

import com.project.exceptions.CustomerException;
import com.project.exceptions.LoginException;
import com.project.exceptions.LogoutException;
import com.project.exceptions.UserException;
import com.project.model.CurrentCustomerSession;
import com.project.model.Customer;
import com.project.model.User;

public interface LoginLogoutCustomerService {
    public CurrentCustomerSession loginCustomer(User user) throws LoginException, CustomerException;

    public String logoutCustomer(String key) throws LogoutException;

    public User authenticateCustomer(User user, String key) throws UserException, LoginException, CustomerException;

    public Customer validateCustomer(String key) throws LoginException, CustomerException;
}
