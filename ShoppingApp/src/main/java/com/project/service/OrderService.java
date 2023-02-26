package com.project.service;

import java.time.LocalDate;
import java.util.List;

import com.project.exceptions.CartException;
import com.project.exceptions.LoginException;
import com.project.exceptions.OrderException;
import com.project.model.Order;


public interface OrderService {

	public Order addOrder(Order order, String key) throws OrderException, CartException, LoginException;
	public Order updateOrder(Order order, String key) throws OrderException, LoginException;
	public Order removeOrder(Integer oriderId, String key) throws OrderException;
	public Order viewOrder(Integer orderId, String key) throws OrderException,LoginException;
	public List<Order> viewAllOrderByDate(LocalDate date) throws OrderException, LoginException;
	public List<Order> viewAllOrderByUserId(Integer userid) throws OrderException;
	
	
}
