package com.project.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Customer;
import com.project.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

	public List<Order> findByCustomers(Integer userid);

	public List<Order> findByOrderDate(LocalDate date);

	
	
	
	
	
}
