package com.project.service;

import java.util.List;

import com.project.exceptions.AddressException;
import com.project.exceptions.CurrentCustomerSessionException;
import com.project.model.Address;

public interface AddressService {

	public Address addAddress(Address address,String key) throws CurrentCustomerSessionException,AddressException;
	
	public Address updateAddress(Address address,String key)throws CurrentCustomerSessionException,AddressException;
	
	public Address removeAddress(Integer addressId,String key)throws CurrentCustomerSessionException,AddressException;
	
	public Address findAddress(Integer addressId,String key)throws CurrentCustomerSessionException,AddressException;
	
	public List<Address> findAllAddress(String key)throws CurrentCustomerSessionException,AddressException;
	
}
