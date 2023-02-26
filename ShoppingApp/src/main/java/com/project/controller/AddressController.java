package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.exceptions.AddressException;
import com.project.exceptions.CurrentCustomerSessionException;
import com.project.model.Address;
import com.project.service.AddressService;

import jakarta.validation.Valid;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@PostMapping("/save")
	public ResponseEntity<Address> saveAddress(@Valid @RequestBody Address address,@RequestParam String key) throws AddressException, CurrentCustomerSessionException{
		
		Address savedAddress = addressService.addAddress(address,key);
		
		return new ResponseEntity<>(savedAddress,HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Address>> getAllAddress(@RequestParam String key) throws AddressException, CurrentCustomerSessionException{
		
		List<Address> addresses = addressService.findAllAddress(key);
		
		return new ResponseEntity<>(addresses,HttpStatus.OK);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Address> updateAddress(@Valid @RequestBody Address address,@RequestParam String key)throws AddressException, CurrentCustomerSessionException{
		
		Address updatedAddress = addressService.updateAddress(address,key);
		
		return new ResponseEntity<>(updatedAddress,HttpStatus.OK);
	}
	
	@DeleteMapping("/remove")
	public Address deleteAddress(@PathVariable Integer addressId,@RequestParam String key)throws AddressException, CurrentCustomerSessionException {
		
		Address address = addressService.findAddress(addressId,key);
		
		addressService.removeAddress(addressId,key);
		
		return address;
		
	}
	
}
