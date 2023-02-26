package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.ProductDto;
import com.project.exceptions.CartException;
import com.project.exceptions.LoginException;
import com.project.exceptions.ProductException;
import com.project.model.Cart;
import com.project.service.CartService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cart")
public class CartController {
    
	@Autowired
	private CartService car;
	
	@PostMapping("/add")
	public ResponseEntity<Cart> addtoCart(@RequestParam Integer productId, @RequestParam Integer quantity, @RequestParam String key) throws CartException,LoginException,ProductException
	{
		
		Cart cartIteam = car.addProductToCart(productId, quantity, key);
		
		return new ResponseEntity<Cart>(cartIteam, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/cart/remove")
	public ResponseEntity<List<ProductDto>> removeProductFromCart(@RequestParam Integer productid,@RequestParam  String key) throws CartException, LoginException
	{
		List<ProductDto> list = car.removeProductfromCart(productid, key);
		
		return new ResponseEntity<List<ProductDto>>(list, HttpStatus.OK);
	}
	
	
	@GetMapping("/getproduct")
	public ResponseEntity<List<ProductDto>> getallcart(@RequestParam String key) throws CartException,LoginException
	{
		List<ProductDto> s = car.viewAllProducts(key);
		
		return new ResponseEntity<List<ProductDto>>(s, HttpStatus.OK);
	}
  
    
  
    
    @PutMapping("/products") 
    public ResponseEntity<List<ProductDto>> updateProductQuantity(
												    		  @RequestParam Integer productId , 
															  @RequestParam Integer quantity,
															  @RequestParam String key) throws CartException, LoginException, ProductException {
    
    	List<ProductDto> list = car.updateProductQuantity(productId, quantity, key);
    	
    	return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    
    @DeleteMapping("/products")
    public ResponseEntity<Cart> removeAllProducts(@RequestParam String key) throws CartException, LoginException {
    	Cart cart = car.removeAllproduct(key);
    	
    	return new ResponseEntity<>(cart, HttpStatus.OK);
    }
    

}
