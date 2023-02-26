package com.project.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.CurrentCustomerSession;
import com.project.dto.AddressDto;
import com.project.dto.ProductDto;
import com.project.exceptions.CartException;
import com.project.exceptions.LoginException;
import com.project.exceptions.OrderException;
import com.project.model.Address;
import com.project.model.Cart;
import com.project.model.Customer;
import com.project.model.Order;
import com.project.repository.*;
@Service
public class OrderServiceImpl implements OrderService{
    
	@Autowired
	private OrderRepository orderRepoSitory;
	

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private CartRepo cartRepo;
	@Autowired
	private CurrentCustomerSessionRepo sessionRepo;

	
	
	
	@Override
	public Order addOrder(Order order, String key) throws OrderException, CartException, LoginException {
		
		 Optional<CurrentCustomerSession> user = sessionRepo.findByKey(key);
		 
		 if( user.isPresent() ) {
			 
			 Integer customerId = user.get().getCustomerId();
			 
			 Optional<Customer> ourCustomer = customerRepo.findById(customerId);
			 
			 Address addr = ourCustomer.get().getAddress();
			 
			 
			 Order currOrder = new Order();
			 
			 currOrder.setOrderDate(LocalDate.now());
			 currOrder.setOrderAddress(new AddressDto(addr.getStreetNo(), addr.getBuildingName(), addr.getCity(), addr.getState(), addr.getCountry(), addr.getPincode()));
			 
			 currOrder.setCustomers(ourCustomer.get());
			 currOrder.setStatus("Order confirmed");
			 
			 
			 List<ProductDto> list = cartRepo.findByCustomer(ourCustomer.get()).getProducts();
			 
			 if( list.size() == 0) {
				 throw new CartException("Add product to the cart first...");
			 }
			 
			 List<ProductDto> productList = new ArrayList<>();

			 Double finalPrice = 0.0 ;
			 
			 for(ProductDto proDto : list) {
				 
				 productList.add(proDto);
				 
				 finalPrice += (proDto.getPrice() * proDto.getQuantity()) ;
				 
			 }
			 
			 currOrder.setTotal(finalPrice);	
			 currOrder.setPList(productList); 
			 
			 Cart customerCart = cartRepo.findByCustomer(ourCustomer.get()) ;
			 
			 customerCart.setProducts(new ArrayList<>());
			 
			 cartRepo.save(customerCart);
			 
			 return orderRepoSitory.save(currOrder);
			 
		 }
		 else {
			 throw new LoginException("Login first");
		 }
		 
		 
	}

	@Override
	public Order updateOrder(Order order, String key) throws OrderException, LoginException {
		 Optional<CurrentCustomerSession> user = sessionRepo.findByKey(key);

		if( user.isPresent() ) {
		
			Optional<Order> opt=  orderRepoSitory.findById(order.getOrderId());
			
			if(opt.isPresent()) {
				return orderRepoSitory.save(order);
			}
			else {
				throw new OrderException("Order does not exist");
			}
		}
		else {
			throw new LoginException("Please, Login First...");
		}
		
	}

	@Override
	public Order removeOrder(Integer orderId, String key) throws OrderException {
		 Optional<CurrentCustomerSession> user = sessionRepo.findByKey(key);

			if( user.isPresent() ) {
			
		Order	existingProduct = orderRepoSitory.findById(orderId).orElseThrow(()->new OrderException("Order does not exist with id :"));
		
		orderRepoSitory.delete(existingProduct);
		
		return existingProduct;
			}	else {
				throw new LoginException("Please, Login First...");
			}
	}


	@Override
	public List<Order> viewAllOrderByDate(LocalDate date) throws OrderException,LoginException {
		List<Order> Order= orderRepoSitory.findByOrderDate(date);
		
		if(Order.size()>0) {
			
			return Order;
		}
		else {
			throw new OrderException("Order doesn't exist on this date.");
		}
		
	}

	@Override
	public List<Order> viewAllOrderByUserId(Integer userid) throws OrderException {

		List<Order> list = orderRepoSitory.findByCustomers(userid);
		
		if( list.size() < 1) {
			throw new OrderException("No order found with this userId.");
		}
		
		return list;
	}

	@Override
	public Order viewOrder(Integer orderId,String key) throws OrderException {

		 Optional<CurrentCustomerSession> user = sessionRepo.findByKey(key);

			if( user.isPresent() ) {
			
	Optional<Order> order=  orderRepoSitory.findById(orderId);
	
	if(order.isPresent()) {
		return order.get();
	}
	else {
		throw new OrderException("No order found");
	}
			}
			else {
				throw new LoginException("Please, Login First...");
			}
	}



}
