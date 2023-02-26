package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exceptions.AddressException;
import com.project.exceptions.CurrentCustomerSessionException;
import com.project.model.Address;
import com.project.model.CurrentCustomerSession;
import com.project.repository.AddressRepository;
import com.project.repository.CurrentCustomerSessionRepo;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;


    @Autowired
    private CurrentCustomerSessionRepo currentCustomerSessionRepo;
	
	@Override
	public Address addAddress(Address address,String key) throws CurrentCustomerSessionException,AddressException {
		 Optional<CurrentCustomerSession> optional_CurrentCustomerSession = currentCustomerSessionRepo.findByKey(key);

	        if (optional_CurrentCustomerSession.isEmpty()) { throw new CurrentCustomerSessionException("Invalid key, Please Login In !");}

		addressRepository.saveAndFlush(address);
		
		return address;
	}

	@Override
	public Address updateAddress(Address address,String key)throws CurrentCustomerSessionException,AddressException  {
		 Optional<CurrentCustomerSession> optional_CurrentCustomerSession = currentCustomerSessionRepo.findByKey(key);

	        if (optional_CurrentCustomerSession.isEmpty()) { throw new CurrentCustomerSessionException("Invalid key, Please Login In !");}



		  Optional<Address> opt =	addressRepository.findById(address.getAddressId());
	       if(opt.isPresent()) {
	    	   return addressRepository.save(address);
	       }
	       else {
			throw new AddressException("Address not found");
		}		
	}

	@Override
	public Address removeAddress(Integer addressId,String key) throws CurrentCustomerSessionException,AddressException {
		 Optional<CurrentCustomerSession> optional_CurrentCustomerSession = currentCustomerSessionRepo.findByKey(key);

	        if (optional_CurrentCustomerSession.isEmpty()) { throw new CurrentCustomerSessionException("Invalid key, Please Login In !");}



		Address address = addressRepository.findById(addressId).orElseThrow(() -> new AddressException("No address found"));
		
		addressRepository.delete(address);
		
		return address;
		
	}

	@Override
	public Address findAddress(Integer addressId,String key)throws CurrentCustomerSessionException,AddressException  {
		 Optional<CurrentCustomerSession> optional_CurrentCustomerSession = currentCustomerSessionRepo.findByKey(key);

	        if (optional_CurrentCustomerSession.isEmpty()) { throw new CurrentCustomerSessionException("Invalid key, Please Login In !");}



		Address address = addressRepository.findById(addressId).orElseThrow(() -> new AddressException("No address belongs to the provided id"));
		
		return address;
	}

	@Override
	public List<Address> findAllAddress(String key) throws CurrentCustomerSessionException,AddressException  {
		 Optional<CurrentCustomerSession> optional_CurrentCustomerSession = currentCustomerSessionRepo.findByKey(key);

	        if (optional_CurrentCustomerSession.isEmpty()) { throw new CurrentCustomerSessionException("Invalid key, Please Login In !");}


		
		List<Address> addresses = addressRepository.findAll();
		
		if(addresses.size()==0) {
			throw new AddressException("No address found in database");
		}
		return addresses;
		
	}

}
