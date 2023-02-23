package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exceptions.AddressException;
import com.project.model.Address;
import com.project.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AddressService addressService;
	
	
	@Override
	public Address addAddress(Address address) {

		addressRepository.saveAndFlush(address);
		
		return address;
	}

	@Override
	public Address updateAddress(Address address) {

		Address add = addressService.updateAddress(address);
		
		if(address.getAddressId() == add.getAddressId()) {
			
			addressRepository.saveAndFlush(address);
		}
		
		return address;
		
	}

	@Override
	public Address removeAddress(Integer addressId) {

		Address address = addressRepository.findById(addressId).orElseThrow(() -> new AddressException("No address found"));
		
		addressRepository.delete(address);
		
		return address;
		
	}

	@Override
	public Address findAddress(Integer addressId) {

		Address address = addressRepository.findById(addressId).orElseThrow(() -> new AddressException("No address belongs to the provided id"));
		
		return address;
	}

	@Override
	public List<Address> findAllAddress() {
		
		List<Address> addresses = addressService.findAllAddress();
		
		if(addresses.size()==0) {
			throw new AddressException("No address found in database");
		}
		return addresses;
		
	}

}
