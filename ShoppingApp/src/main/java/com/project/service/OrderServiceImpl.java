package com.project.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.repository.CartRepo;
import com.project.repository.CurrentCustomerSessionRepo;
import com.project.repository.OrderProductRepository;
import com.project.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{
    
	@Autowired
	private OrderRepository orderRepoSitory;
	
	@Autowired
	private OrderProductRepository orderProductDao;
	//CartProduct Repo
	@Autowired
	private CartRepo cartProductdao;
	//Cart REpo
	@Autowired
	private CartRepo cartDao;
	@Autowired
	private CurrentCustomerSessionRepo cusRepo;
	
	@Autowired
	private ProductRepository ProductRepository;
	
	
	
	@Autowired
	private CustomerDao customerDao;
	
	
	@Override
	public OrderDTO addOrder(String key) throws OrderException, CustomerException {
		
       Customer customer = cusService.getCustomerDetails(key);
		
		if(customer==null) {
			throw new CustomerException("Please login to add the product to cart");
		}
		
		Optional<Cart> optcart = cartDao.findByCustomer(customer);
		
		if(optcart.isPresent()) {

			 Cart cart = optcart.get();
			 
			 List<CartProduct> list = cartProductdao.getByCartId(cart.getCartId());
			 
			 if(list.size()==0) {
				 throw new OrderException("Please add the items to your cart to place the order");
			 }
			 Order newOrder = new Order();
			 newOrder.setLocalDate(LocalDate.now());
			 newOrder.setStatus("Placed");
			 newOrder.setCustomers(customer);
			 Order order = orderDao.save(newOrder);
			 customer.getOrder().add(order);
			 customerDao.save(customer);
			 List<ProductDTO> productDto = new ArrayList<>();
			 Double totalPrice = 0.0;
			 for(CartProduct carProd: list) {
				 OrderProduct orderProduct = new OrderProduct(order.getOrderId(), carProd.getProductId(), carProd.getQuantity());
				 orderProductDao.save(orderProduct);
				 Product product = productDao.findById(carProd.getProductId()).get();
				 ProductDTO proDuc = new ProductDTO(carProd.getProductId(), product.getProductName(), product.getPrice(), carProd.getQuantity(), product.getPrice()*carProd.getQuantity());
				 productDto.add(proDuc);
				 totalPrice+=product.getPrice()*carProd.getQuantity();
			 }
			 OrderDTO orderDTO = new OrderDTO(order.getOrderId(), customer.getFirstName(), productDto, order.getLocalDate(), totalPrice);
			 return orderDTO;
		}
		else {
			throw new OrderException("Please add the items to your cart to place the order");
		}
	}

	@Override
	public OrderDTO viewOrder(String key, Integer orderId) throws OrderException, CustomerException {
		Customer customer = cusService.getCustomerDetails(key);
			
			if(customer==null) {
				throw new CustomerException("Please login to view the order details");
			} 
		
		Optional<Order> opt=orderDao.findById(orderId);
		 
		 if(!opt.isPresent()) {
			 throw new OrderException("No order found with this Id :"+" "+orderId);
		 }
		 
		 Order order = opt.get();
		 List<OrderProduct> list = orderProductDao.getByOrderId(orderId);
		 List<ProductDTO> productDto = new ArrayList<>();
		 Double totalPrice = 0.0;
		 for(OrderProduct carProd: list) {
			 Product product = productDao.findById(carProd.getProductId()).get();
			 ProductDTO proDuc = new ProductDTO(carProd.getProductId(), product.getProductName(), product.getPrice(), carProd.getQuantity(), product.getPrice()*carProd.getQuantity());
		     productDto.add(proDuc);
			 totalPrice+=product.getPrice()*carProd.getQuantity();
		 }
		 OrderDTO orderDTO = new OrderDTO(order.getOrderId(), order.getCustomers().getFirstName(), productDto, order.getLocalDate(), totalPrice);
		 return orderDTO;
		
	}

	@Override
	public List<OrderDTO> listOfOrder(String key) throws OrderException, CustomerException {
		
		 Customer customer = cusService.getCustomerDetails(key);
			
			if(customer==null) {
				throw new CustomerException("Please login to add the product to cart");
			}
		
		   List<Order> listOfOrder = customer.getOrder();
		   
		   if(listOfOrder.size()==0) {
			   throw new OrderException("You have no order history");
		   }
		   
		   List<OrderDTO> orderDTOList = new ArrayList<>();
		   for(Order order:listOfOrder) {
			   List<OrderProduct> list = orderProductDao.getByOrderId(order.getOrderId());
			   List<ProductDTO> productDto = new ArrayList<>();
				 Double totalPrice = 0.0;
				 for(OrderProduct carProd: list) {
					 Product product = productDao.findById(carProd.getProductId()).get();
					 ProductDTO proDuc = new ProductDTO(carProd.getProductId(), product.getProductName(), product.getPrice(), carProd.getQuantity(), product.getPrice()*carProd.getQuantity());
				     productDto.add(proDuc);
					 totalPrice+=product.getPrice()*carProd.getQuantity();
				 }
				 OrderDTO orderDTO = new OrderDTO(order.getOrderId(), order.getCustomers().getFirstName(), productDto, order.getLocalDate(), totalPrice);
				
           orderDTOList.add(orderDTO);  
		   }
		return orderDTOList;
		
	}

	@Override
	public List<OrderDTO> listOfOrderByCustomerId(Integer customerId) throws OrderException, CustomerException {
		
		Optional<Customer> opt = customerDao.findById(customerId);
		
		if(!opt.isPresent()) {
			throw new CustomerException("There is no customer with this id :"+" "+customerId);
		}
		
		Customer customer = opt.get();
		
		 List<Order> listOfOrder = customer.getOrder();
		   
		   if(listOfOrder.size()==0) {
			   throw new OrderException("You have no order history");
		   }
		   
		   List<OrderDTO> orderDTOList = new ArrayList<>();
		   for(Order order:listOfOrder) {
			   List<OrderProduct> list = orderProductDao.getByOrderId(order.getOrderId());
			   List<ProductDTO> productDto = new ArrayList<>();
				 Double totalPrice = 0.0;
				 for(OrderProduct carProd: list) {
					 Product product = productDao.findById(carProd.getProductId()).get();
					 ProductDTO proDuc = new ProductDTO(carProd.getProductId(), product.getProductName(), product.getPrice(), carProd.getQuantity(), product.getPrice()*carProd.getQuantity());
				     productDto.add(proDuc);
					 totalPrice+=product.getPrice()*carProd.getQuantity();
				 }
				 OrderDTO orderDTO = new OrderDTO(order.getOrderId(), order.getCustomers().getFirstName(), productDto, order.getLocalDate(), totalPrice);
				
         orderDTOList.add(orderDTO);  
		   }
		return orderDTOList;
		
	}

}
