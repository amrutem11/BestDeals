package com.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.dto.ProductDto;
import com.project.exceptions.CartException;
import com.project.exceptions.LoginException;
import com.project.exceptions.OrderExcetion;
import com.project.exceptions.ProductException;
import com.project.model.Cart;
import com.project.model.Product;
import com.project.model.Session;
import com.project.model.Customer;
import com.project.repository.CartRepo;
import com.project.repository.CustomerRepo;
import com.project.repository.ProductRepository;
import com.project.repository.Productdtorepo;
import com.project.repository.Sessionrepo;

public class CartServiceimpl implements CartService {
     

	  private static final String Customer = null;

	@Autowired
	  private CartRepo cartrepo;
	   
	  @Autowired
	  private ProductRepository prorepo;
	  
	  @Autowired
	  private Productdtorepo pto;
	  
	  @Autowired
	  private Sessionrepo srepo;
	  
	   @Autowired
	  private CustomerRepo curepo;
	
	@Override
	public Cart addProductToCart(Integer productId, Integer quantity, String key)
			throws CartException, LoginException, ProductException {
		// TODO Auto-generated method stub
		   Session currentCustomer  = srepo.findByuuid(key);
		   
		   if(currentCustomer == null) {
				throw new LoginException("Please provide a valid key to update a customer");
			}
		   else {
			   Optional<Product> optProduct = prorepo.findById(productId) ;
			   
			   if(optProduct.isEmpty()) {
					throw new ProductException("No product available with id :"+ productId) ;
				}
			
			   Optional<Customer> fin = curepo.findById(currentCustomer.getUserId());
			   
			   Customer or = fin.get();
				Product currentProduct = optProduct.get();
				
				if(currentProduct.getQuantity() < quantity) {
					throw new ProductException("Product quantity not available or Out of stock") ;
				}
				
				Cart customerCart = cartrepo.getCart(or.getCustomerId());
				
                 if(customerCart == null) { // user is adding first time in the cart 
					
					customerCart = new Cart();
					
				
					customerCart.setCustomer(or);
					
					List<ProductDto> list = customerCart.getProducts();
					
					ProductDto productDto = new ProductDto( currentProduct.getProductId(),
															currentProduct.getProductName(),
															currentProduct.getPrice(), 
															currentProduct.getColor(), 
															currentProduct.getDimension(),
															currentProduct.getManufacturer(),
															quantity);
					
					currentProduct.setQuantity(currentProduct.getQuantity() - quantity);
					
					list.add(productDto);
					
					
					cartrepo.save(customerCart) ;
					prorepo.save(currentProduct);
					
					return customerCart;
						
				}
                 else {
 					
 					List<ProductDto> list = customerCart.getProducts();
 					
 					ProductDto productDto = new ProductDto( currentProduct.getProductId(),
 															currentProduct.getProductName(),
 															currentProduct.getPrice(), 
 															currentProduct.getColor(), 
 															currentProduct.getDimension(),
 															currentProduct.getManufacturer(), 
 															quantity);
 					
 					currentProduct.setQuantity(currentProduct.getQuantity() - quantity);
 					
 					list.add(productDto);
 					
 					
 					cartrepo.save(customerCart) ;
 					prorepo.save(currentProduct);
 					 
 					return customerCart;
 			   
 				}
		   }
		   
		   
	}

	@Override
	public List<ProductDto> removeProductfromCart(Integer productId, String key)
			throws CartException, LoginException, ProductException {
		// TODO Auto-generated method stub
    Session currentCustomer = srepo.findByuuid(key);
		
		if(currentCustomer==null)
		{
			throw new LoginException("You are Not Authorized to Delete the Product");
		}
          Optional<ProductDto> optProduct = pto.findById(productId) ;
		
		if(optProduct.isEmpty()) {
			throw new ProductException("No product available with id :"+ productId) ;
		}
		else
		{
			//Integer s = currentCustomer.getUserId();
			
			
		 Optional<Customer> fin = curepo.findById(currentCustomer.getUserId());
			
			
			Customer or = fin.get();
			
			
			ProductDto currentProduct = optProduct.get();
			
			Cart customerCart = cartrepo.getCart(or.getCustomerId());
			
			
			if(customerCart==null)
			{
				throw new OrderExcetion("NO order found for this ");
			}
			else
			{
				cartrepo.deleteById(productId);
				
				cartrepo.save(customerCart);
				List<ProductDto> list = customerCart.getProducts();
				
				
				return list;
				
			}
		}
	}

	@Override
	public List<ProductDto> updateProductQuantity(Integer productId, Integer quantity, String key)
			throws CartException, LoginException, ProductException {
		// TODO Auto-generated method stub
          Session currentCustomer = srepo.findByuuid(key);
		
		
		if(currentCustomer==null)
		{
			throw new LoginException("You are not Authorized to Update the Product");
		}
		
          Optional<Product> optProduct =  prorepo.findById(productId) ;
		
		if(optProduct.isEmpty()) {
			throw new ProductException("No product available with id :"+ productId) ;
		}
		
		Product currentProduct = optProduct.get();
		
		if(currentProduct.getQuantity() < quantity) {
			throw new ProductException("Product Out of stock") ;
		}
		
		
          Integer s = currentCustomer.getUserId();
		
		Optional<Customer> fin = curepo.findById(s);
		
		
		Customer or = fin.get();
		
		

		
		Cart customerCart = cartrepo.getCart(or.getCustomerId());
		
		
	
		
		if(customerCart != null) {
			
			List<ProductDto> list = customerCart.getProducts();
			
			boolean flag = false;
			
			for(ProductDto productdto : list) {
				
				if(productdto.getProductId() == productId) {
					
					flag = true;
					
					currentProduct.setQuantity(currentProduct.getQuantity() - quantity);
					productdto.setQuantity(productdto.getQuantity() + quantity);
					
					prorepo.save(currentProduct) ;
					pto.save(productdto) ;
					
					break;
				}
				
			}
			
			if(!flag) {
				throw new ProductException("There was no product in your cart with this id: "+" "+productId) ;
			}
			
			return list;
		}
		else {
			throw new ProductException("You have no product in the cart to update the quantity");
		}
		
	
		
	}

	@Override
	public Cart removeAllproduct(String key) throws CartException, LoginException {
		// TODO Auto-generated method stub
        Session currentCustomer = srepo.findByuuid(key);
		
		if(currentCustomer==null)
		{
			throw new LoginException("You are not Authorized to delter");
		}
		
		
		
        Integer s = currentCustomer.getUserId();
		
		Optional<Customer> fin = curepo.findById(s);
		
		
		Customer or = fin.get();
		
		

		
		Cart customerCart = cartrepo.getCart(or.getCustomerId());
		
	
		
		List<ProductDto> list = customerCart.getProducts();
		System.out.println("Hi");
		if(list.size() > 0) {
			
			
			for(ProductDto productDto : list) {
				
				Optional<Product> opt = prorepo.findById(productDto.getProductId()) ;
				
				Product currentProduct = opt.get();
				
				currentProduct.setQuantity(currentProduct.getQuantity() + productDto.getQuantity());
				
				pto.delete(productDto);
				
				prorepo.save(currentProduct) ;
			}
			
		}
		
		customerCart.setProducts(new ArrayList<>());
		
		return cartrepo.save(customerCart) ;
		
	}

	@Override
	public List<ProductDto> viewAllProducts(String key) throws CartException, LoginException {
		// TODO Auto-generated method stub
		  Session ss  = srepo.findByuuid(key);
		    
		     if(ss==null)
		     {
		    	 throw new LoginException("You are not Authorized to Check Cart");
		     }
		     else
		     {
		    	 Integer s = ss.getUserId();
		 		
		 		Optional<Customer> fin = curepo.findById(s);
		 		
		 		
		 		Customer or = fin.get();
		 		
		 		

		 		
		 		Cart customerCart = cartrepo.getCart(or.getCustomerId());
		    	 

		 		
		 		if(customerCart == null) {
		 			throw new CartException("You dont have anything in your cart");
		 		}
		 		
		 		return customerCart.getProducts();
		     }
			
		}
	}


