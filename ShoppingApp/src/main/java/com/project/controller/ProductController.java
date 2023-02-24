package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Product;
import com.project.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/save")
	public ResponseEntity<Product> saveCProduct(@Valid @RequestBody Product product) {
		
		Product addedProduct = productService.saveProduct(product);
		
		return new ResponseEntity<>(addedProduct,HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll")
	 public ResponseEntity<List<Product>> getAllProduct() {
		
	        List<Product> products = productService.findAllProducts();
	        
	        return new ResponseEntity<>(products,HttpStatus.OK);
	 }
	
	
	@GetMapping("/getProduct/{productId}")
	public ResponseEntity<Product> getCategoryById(@PathVariable int productId) {
		
		Product product = productService.findProductById(productId);
		
		
		return new ResponseEntity<>(product,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteProduct/{productId}")
	public Product deleteProduct(@PathVariable Integer productId) {
			
			Product product = productService.deleteProduct(productId);
			
			productService.deleteProduct(productId);
			
			return product;
			
	}
	
	@PutMapping("/updateProduct")
	public ResponseEntity<Product> updateProduct( @Valid @RequestBody Product product) {
			
	  Product updatedProduct = productService.updateProduct(product);
	  
	  
	  return new ResponseEntity<>(updatedProduct,HttpStatus.ACCEPTED);
		
	}
}
