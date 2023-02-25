package com.project.service;

import java.util.List;

import com.project.dto.ProductDto;
import com.project.exceptions.CartException;
import com.project.exceptions.LoginException;
import com.project.exceptions.ProductException;
import com.project.model.Cart;

public interface CartService {
     
 public Cart addProductToCart(Integer productId, Integer quantity, String key) throws CartException ,LoginException,ProductException;
	
	
	public List<ProductDto> removeProductfromCart(Integer productId, String key) throws CartException,LoginException,ProductException;
	
	
	public List<ProductDto> updateProductQuantity(Integer productId, Integer quantity, String key)throws CartException,LoginException,ProductException;
	
	
	
	public Cart removeAllproduct(String key) throws CartException,LoginException;
	
	public List<ProductDto> viewAllProducts(String key) throws CartException,LoginException;
	
}
