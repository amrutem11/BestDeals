package com.project.service;

import java.util.List;
import java.util.Optional;

import com.project.model.Category;

public interface CategoryService {
	
	public Category saveCategory(Category category);

    public List<Category> findAllCategories();

    public Optional<Category> findCategoryById(Integer categoryId);

    public String deleteCategory(Integer categoryId);

    public String updateCategory(Category category);

}
