package com.project.controller;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.exceptions.CartException;
import com.project.exceptions.LoginException;
import com.project.exceptions.OrderException;
import com.project.model.Customer;
import com.project.model.Order;
import com.project.repository.CustomerRepo;
import com.project.service.OrderService;


@RestController
public class OrderController {
    
	@Autowired
    private  OrderService orderService;
	
	@Autowired
	private CustomerRepo customerRepo;
    
    @PostMapping("/Order")
    public ResponseEntity<Order> addOrderHandler(@RequestBody Order order,@RequestParam String key) throws OrderException, CartException, LoginException{
    	
    	Order addedOrder = orderService.addOrder(order, key);
    	
    	return new ResponseEntity<Order>(addedOrder, HttpStatus.CREATED);
    }
    
    @PutMapping("/Order")
    public ResponseEntity<Order> updateOrderHandler(@RequestBody Order order, @RequestParam String key) throws OrderException, LoginException{
    	
    	Order updatedOrder = orderService.updateOrder(order, key);
    	
    	return new ResponseEntity<Order>(updatedOrder, HttpStatus.CREATED);
    	
    }
    
    @DeleteMapping("/Order/{orderid}")
    public ResponseEntity<Order> removeOrderHandler(@PathVariable("orderid") Integer orderId, @RequestParam String uuid) throws OrderException{
    	
    	Order deletedOrder = orderService.removeOrder(orderId, uuid);
    	
    	return new ResponseEntity<Order>(deletedOrder, HttpStatus.OK);
    	
    }
    
    @GetMapping("/Order")
    public ResponseEntity<Order> viewOrderHandler(@RequestParam Integer orderId,@RequestParam String key) throws OrderException{
    	
    	Order existingOrder = orderService.viewOrder(orderId,key);
    	
    	return new ResponseEntity<Order>(existingOrder, HttpStatus.OK);
    	
    }
    
    @GetMapping("/allOrder/date/{date}")
    public ResponseEntity<List<Order>> getAllOrderBydateHandler(@PathVariable("date") LocalDate date,@RequestParam String key) throws OrderException{
    	
    	List<Order> Order = orderService.viewAllOrderByDate(date);
    	
    	return new ResponseEntity<List<Order>>(Order, HttpStatus.OK);
    	
    }

    
    @GetMapping("/allOrder")
    public ResponseEntity<List<Order>> getAllOrderByUserIdHandler(@RequestParam Integer userId) throws OrderException{

    	Optional<Customer> opt = customerRepo.findById(userId);
    	 
    	Customer currentCustomer = opt.get();
    	
    	
    	List<Order> Order = orderService.viewAllOrderByUserId(userId);
    	
    	return new ResponseEntity<List<Order>>(Order, HttpStatus.OK);
    	
    }
    
    

}
