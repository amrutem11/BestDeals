package com.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exceptions.ProductException;
import com.project.model.Product;
import com.project.repository.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService{

	
	@Autowired
    private ProductRepository productRepository;
	
	@Override
	public Product saveProduct(Product product) {

		productRepository.saveAndFlush(product);
		
        return product;
	}

	@Override
	public List<Product> findAllProducts() {
		
		List<Product> products = productRepository.findAll();
         
		if(products.isEmpty()) {
			throw new ProductException("No product found");
		}
		
        return products;
        
	}

	@Override
	public List<Product> findAllProductsForAdmin() {

		List<Product> products = new ArrayList<>();
		
        productRepository.findAll().forEach(products::add);
        
        return products;
	}

	@Override
	public Product findProductById(Integer productId) {

		Product product = productRepository.findById(productId).orElseThrow(() -> new ProductException("No product found"));
		
        

        return product;
	}

	@Override
	public Product deleteProduct(Integer productId) {

		Product product = productRepository.findById(productId).orElseThrow(()-> new ProductException("No product found"));

        productRepository.delete(product);
        

        return product;
		
	}

	@Override
	public Product updateProduct(Product product){

		productRepository.saveAndFlush(product);
		
        return product;
	}

//	@Override
//	public List<Product> findProductByCategoryId(Integer categoryId) {
//
////		List<Product> products = productRepository.findProductByCategoryId(categoryId);
//
////       if(products.size()==0) {
////    	   throw new ProductException("No product found for this category id");
////       }
////        return products;
////	}

}
