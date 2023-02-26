package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.dto.ProductDto;

public interface Productdtorepo extends JpaRepository<ProductDto, Integer>  {

}
