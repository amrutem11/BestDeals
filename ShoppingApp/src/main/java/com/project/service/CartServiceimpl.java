package com.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.project.exceptions.OrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.CurrentCustomerSession;
import com.project.dto.ProductDto;
import com.project.exceptions.CartException;
import com.project.exceptions.LoginException;
import com.project.exceptions.ProductException;
import com.project.model.Cart;
import com.project.model.Product;
import com.project.model.Customer;
import com.project.repository.CartRepo;
import com.project.repository.CurrentCustomerSessionRepo;
import com.project.repository.CustomerRepo;
import com.project.repository.ProductRepository;
import com.project.repository.Productdtorepo;
@Service
public class CartServiceimpl implements CartService {
     

	  private static final String Customer = null;

	@Autowired
	  private CartRepo cartrepo;
	   
	  @Autowired
	  private ProductRepository prorepo;
	  
	  @Autowired
	  private Productdtorepo pto;
	  
	  @Autowired
	  private CurrentCustomerSessionRepo srepo;
	  
	   @Autowired
	  private CustomerRepo curepo;
	
	@Override
	public Cart addProductToCart(Integer productId, Integer quantity, String key)
			throws CartException, LoginException, ProductException {
		// TODO Auto-generated method stub
		Optional<CurrentCustomerSession> currentCustomer  = srepo.findByKey(key);
		   
		   if(currentCustomer.isEmpty()) {
				throw new LoginException("Please Login First ");
			}
		   else {
			   Optional<Product> optProduct = prorepo.findById(productId) ;
			   
			   if(optProduct.isEmpty()) {
					throw new ProductException("No product available with id :"+ productId) ;
				}
			
			   Optional<Customer> fin = curepo.findById(currentCustomer.get().getCustomerId());
			   
			   Customer or = fin.get();
				Product currentProduct = optProduct.get();
				
				if(currentProduct.getQuantity() < quantity) {
					throw new ProductException("Product quantity not available or Out of stock") ;
				}
				
				Optional<Cart> customerCart = cartrepo.getCart(or.getCustomerId());
				
                 if(customerCart.isEmpty()) { // user is adding first time in the cart 
					
					Cart customerCart2 = new Cart();
					
				
					customerCart2.setCustomer(or);
					
					List<ProductDto> list = customerCart2.getProducts();
					
					ProductDto productDto = new ProductDto( 0,currentProduct.getProductId(),
															currentProduct.getProductName(),
															currentProduct.getPrice(), 
															currentProduct.getColor(), 
															currentProduct.getDimension(),
															currentProduct.getManufacturer(),
															quantity);
					
					currentProduct.setQuantity(currentProduct.getQuantity() - quantity);
					
					list.add(productDto);
					
					
					cartrepo.save(customerCart2) ;
					prorepo.save(currentProduct);
					
					return customerCart2;
						
				}
                 else {
 					
 					List<ProductDto> list = customerCart.get().getProducts();
 					
 					ProductDto productDto = new ProductDto(0, currentProduct.getProductId(),
 															currentProduct.getProductName(),
 															currentProduct.getPrice(), 
 															currentProduct.getColor(), 
 															currentProduct.getDimension(),
 															currentProduct.getManufacturer(), 
 															quantity);
 					
 					currentProduct.setQuantity(currentProduct.getQuantity() - quantity);
 					
 					list.add(productDto);
 					
 					
 					cartrepo.save(customerCart.get()) ;
 					prorepo.save(currentProduct);
 					 
 					return customerCart.get();
 			   
 				}
		   }
		   
		   
	}

	@Override
	public List<ProductDto> removeProductfromCart(Integer productId, String key)
			throws CartException, LoginException, ProductException, OrderException {
		// TODO Auto-generated method stub
		Optional<CurrentCustomerSession> currentCustomer  = srepo.findByKey(key);
		   
		   if(currentCustomer.isEmpty()) {
				throw new LoginException("Please Login First ");
			}
          Optional<ProductDto> optProduct = pto.findById(productId) ;
		
		if(optProduct.isEmpty()) {
			throw new ProductException("No product available with id :"+ productId) ;
		}
		else
		{
			//Integer s = currentCustomer.getUserId();
			
			
		 Optional<Customer> fin = curepo.findById(currentCustomer.get().getCustomerId());
			
			
			Customer or = fin.get();
			
			
			ProductDto currentProduct = optProduct.get();
			
			Optional<Cart> customerCart = cartrepo.getCart(or.getCustomerId());
			
			
			if(customerCart.isEmpty())
			{
				throw new OrderException("NO order found for this ");
			}
			else
			{
				cartrepo.deleteById(productId);
				
				cartrepo.save(customerCart.get());
				List<ProductDto> list = customerCart.get().getProducts();
				
				
				return list;
				
			}
		}
	}

	@Override
	public List<ProductDto> updateProductQuantity(Integer productId, Integer quantity, String key)
			throws CartException, LoginException, ProductException {
		// TODO Auto-generated method stub
		Optional<CurrentCustomerSession> currentCustomer  = srepo.findByKey(key);
		   
		   if(currentCustomer.isEmpty()) {
				throw new LoginException("Please Login First ");
			}
		
          Optional<Product> optProduct =  prorepo.findById(productId) ;
		
		if(optProduct.isEmpty()) {
			throw new ProductException("No product available with id :"+ productId) ;
		}
		
		Product currentProduct = optProduct.get();
		
		if(currentProduct.getQuantity() < quantity) {
			throw new ProductException("Product Out of stock") ;
		}
		
		
          Integer s = currentCustomer.get().getCustomerId();
		
		Optional<Customer> fin = curepo.findById(s);
		
		
		Customer or = fin.get();
		
		

		
		Optional<Cart> customerCart = cartrepo.getCart(or.getCustomerId());
		
		
	
		
		if(customerCart.isPresent()) {
			
			List<ProductDto> list = customerCart.get().getProducts();
			
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
		Optional<CurrentCustomerSession> currentCustomer  = srepo.findByKey(key);
		   
		   if(currentCustomer.isEmpty()) {
				throw new LoginException("Please Login First ");
			}
		
		
        Integer s = currentCustomer.get().getCustomerId();
		
		Optional<Customer> fin = curepo.findById(s);
		
		
		Customer or = fin.get();
		
		

		
		Optional<Cart> customerCart = cartrepo.getCart(or.getCustomerId());
		
	
		
		List<ProductDto> list = customerCart.get().getProducts();
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
		
		customerCart.get().setProducts(new ArrayList<>());
		
		return cartrepo.save(customerCart.get()) ;
		
	}

	@Override
	public List<ProductDto> viewAllProducts(String key) throws CartException, LoginException {
		// TODO Auto-generated method stub
		Optional<CurrentCustomerSession> currentCustomer  = srepo.findByKey(key);
		   
		   if(currentCustomer.isEmpty()) {
				throw new LoginException("Please Login First ");
			}
		     else
		     {
		    	 Integer s = currentCustomer.get().getCustomerId();
		 		
		 		Optional<Customer> fin = curepo.findById(s);
		 		
		 		
		 		Customer or = fin.get();
		 		
		 		

		 		
		 		Optional<Cart> customerCart = cartrepo.getCart(or.getCustomerId());
		    	 

		 		
		 		if(customerCart.isEmpty()) {
		 			throw new CartException("You dont have anything in your cart");
		 		}
		 		
		 		return customerCart.get().getProducts();
		     }
			
		}
	}


