package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.dto.ProductDto;
import com.project.exceptions.CartException;
import com.project.exceptions.LoginException;
import com.project.exceptions.ProductException;
import com.project.model.Cart;
import com.project.model.Product;
import com.project.model.Session;
import com.project.model.Customer;
import com.project.repository.CartRepo;
import com.project.repository.CustomerRepo;
import com.project.repository.ProductRepository;
import com.project.repository.Sessionrepo;

public class CartServiceimpl implements CartService {
     

	  private static final String Customer = null;

	@Autowired
	  private CartRepo cartrepo;
	   
	  @Autowired
	  private ProductRepository prorepo;
	  
	  @Autowired
	  private ProductDto pto;
	  
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
															currentProduct.getManufacture(),
															quantity);
					
					currentProduct.setQuantity(currentProduct.getQuantity() - quantity);
					
					list.add(productDto);
					
					
					cartrepo.save(customerCart) ;
					pto.save(currentProduct);
					
					return customerCart;
						
				}
		   }
		   
		   return null ;
	}

	@Override
	public List<ProductDto> removeProductfromCart(Integer productId, String key)
			throws CartException, LoginException, ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDto> updateProductQuantity(Integer productId, Integer quantity, String key)
			throws CartException, LoginException, ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart removeAllproduct(String key) throws CartException, LoginException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDto> viewAllProducts(String key) throws CartException, LoginException {
		// TODO Auto-generated method stub
		return null;
	}

}
