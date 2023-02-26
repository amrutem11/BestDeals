package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exceptions.CategoryException;
import com.project.exceptions.ProductException;
import com.project.model.Category;
import com.project.repository.CategoryRepository;


@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category) {
        
        categoryRepository.saveAndFlush(category);
        
        return category;
    }

	@Override
	public List<Category> findAllCategories() {

		List<Category> categories = categoryRepository.findAll();
        
		if(categories.isEmpty()) {
			throw new ProductException("No categories found");
		}
		
        return categories;
	}

	@Override
	public Category findCategoryById(Integer categoryId) {
		

		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryException("No category found"));

        return category;
	}

	@Override
	public String deleteCategory(Integer categoryId) {
		
		Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new CategoryException("no category found"));
		
		return category.getCategoryName()+" deleted successfully";
	}

	@Override
	public String updateCategory(Category category) {
		
		categoryRepository.saveAndFlush(category);
		
		return "category updated successfully";	
	}

}
