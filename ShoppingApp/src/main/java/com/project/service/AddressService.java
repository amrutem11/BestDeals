package com.project.service;

import java.util.List;

import com.project.model.Address;

public interface AddressService {

	public Address addAddress(Address address);
	
	public Address updateAddress(Address address);
	
	public Address removeAddress(Integer addressId);
	
	public Address findAddress(Integer addressId);
	
	public List<Address> findAllAddress();
	
}
