package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>  {

	
	@Query("select p from Product p where p.category.categoryName=?1")
	public List<Product> viewbyCategoryName(String cname);
}
