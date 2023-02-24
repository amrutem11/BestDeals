package com.project.service;
/* @author sheetalBisht
 *
 */

import com.project.exceptions.CurrentCustomerSessionException;
import com.project.exceptions.CustomerException;
import com.project.model.CurrentCustomerSession;
import com.project.model.Customer;

public interface CurrentCustomerSessionService {

    public CurrentCustomerSession getCurrentCustomerSession(String key) throws CurrentCustomerSessionException;

    public Customer getCustomerDetails(String key) throws CurrentCustomerSessionException, CustomerException;

    public Integer getCurrentUserCustomerId(String key) throws CurrentCustomerSessionException;

}