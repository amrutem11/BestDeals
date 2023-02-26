package com.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.exceptions.CustomerException;
import com.project.model.Address;
import com.project.model.Cart;
import com.project.model.Customer;

public interface CartRepo extends JpaRepository<Cart, Integer>{
    
public Cart findByCustomer(Customer customer) throws CustomerException;
	
	
	@Query("select c from Cart c where c.customer.customerId=?1")
	public Optional<Cart >getCart(Integer custId);

}
