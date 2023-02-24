package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.model.Address;
import com.project.service.AddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@PostMapping("/save")
	public ResponseEntity<Address> saveAddress(@Valid @RequestBody Address address){
		
		Address savedAddress = addressService.addAddress(address);
		
		return new ResponseEntity<>(savedAddress,HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Address>> getAllAddress(){
		
		List<Address> addresses = addressService.findAllAddress();
		
		return new ResponseEntity<>(addresses,HttpStatus.OK);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Address> updateAddress(@Valid @RequestBody Address address){
		
		Address updatedAddress = addressService.updateAddress(address);
		
		return new ResponseEntity<>(updatedAddress,HttpStatus.OK);
	}
	
	@DeleteMapping("/remove")
	public Address deleteAddress(@PathVariable Integer addressId) {
		
		Address address = addressService.findAddress(addressId);
		
		addressService.removeAddress(addressId);
		
		return address;
		
	}
	
}
