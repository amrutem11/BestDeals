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

import com.project.model.Category;
import com.project.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	
	@PostMapping("/save")
	public ResponseEntity<Category> saveCategory(@Valid @RequestBody Category category) {
		
		Category addedCategory = categoryService.saveCategory(category);
		
		return new ResponseEntity<>(addedCategory,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/getAll")
	 public ResponseEntity<List<Category>> getCategories() {
		
	        List<Category> categories = categoryService.findAllCategories();
	        
	        return new ResponseEntity<>(categories,HttpStatus.OK);
	 }

	@GetMapping("/getCategory/{categoryId}")
	public ResponseEntity<Category> getCategoryById(@PathVariable int categoryId) {
		
		Category category = categoryService.findCategoryById(categoryId);
		
		
		return new ResponseEntity<>(category,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteCategory/{categoryId}")
	public Category deletecategoryById(@PathVariable int categoryId) {
			
			Category category = categoryService.findCategoryById(categoryId);
			
			categoryService.deleteCategory(categoryId);
			
			return category;
			
	}
	
	@PutMapping("/updateCategory")
	public ResponseEntity<Category> updateCategory( @Valid @RequestBody Category category) {
			
	  Category updatedCategory = categoryService.findCategoryById(category.getCategoryId());
	  
	  return new ResponseEntity<>(updatedCategory,HttpStatus.ACCEPTED);
		
	}
}
