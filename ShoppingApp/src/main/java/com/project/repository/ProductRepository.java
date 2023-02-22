package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>  {

	
	List<Product> findProductByCategoryId(Integer categoryId);
}
