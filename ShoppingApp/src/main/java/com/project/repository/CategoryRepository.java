package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
