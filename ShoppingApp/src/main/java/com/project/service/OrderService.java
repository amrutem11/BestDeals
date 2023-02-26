package com.project.service;

import java.util.List;

import com.project.exceptions.CustomerException;
import com.project.exceptions.OrderException;
import com.project.model.OrderDTO;


public interface OrderService {

	public OrderDTO addOrder(String key) throws OrderException, CustomerException;
	
	public OrderDTO viewOrder(String key, Integer orderId) throws OrderException, CustomerException;
	
	public List<OrderDTO> listOfOrder(String key) throws OrderException, CustomerException;
	
	public List<OrderDTO> listOfOrderByCustomerId(Integer customerId)throws OrderException, CustomerException;
	
	
	
}
