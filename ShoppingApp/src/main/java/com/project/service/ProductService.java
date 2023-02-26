package com.project.service;

import java.util.List;
import java.util.Optional;

import com.project.model.Product;

public interface ProductService {

	
	public Product saveProduct(Product product);

    public List<Product> findAllProducts();

    public List<Product> findAllProductsForAdmin();

    public Product findProductById(Integer id);

    public Product deleteProduct(Integer id);

    public Product updateProduct(Product product);

//    public List<Product> findProductByCategoryId(Integer categoryId);

    
}
