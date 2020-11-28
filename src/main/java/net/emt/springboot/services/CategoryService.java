package net.emt.springboot.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import net.emt.springboot.exception.ResourceNotFoundException;
import net.emt.springboot.model.Category;
import net.emt.springboot.repository.CategoryRepository;


@Component
public class CategoryService {

	@Autowired CategoryRepository categoryRepository;
	
	public List<Category> getAllCategories() {
		return this.categoryRepository.findAll();
	}
	
	public ResponseEntity<Category> getCategoryById(Long categoryId) throws ResourceNotFoundException{
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found for this id ::" + categoryId));	
		return ResponseEntity.ok().body(category);
	}
	
	public Category saveCategory(Category category) {
		return this.categoryRepository.save(category);
	}
	
	public ResponseEntity<Category> updateCategory(Long categoryId, Category categoryDetails) throws ResourceNotFoundException {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found for this id :: " + categoryId));
		
		category.setCategoryName(categoryDetails.getCategoryName());
		
		return ResponseEntity.ok(this.categoryRepository.save(category));
	}
	
	public Map<String, Boolean> deleteCourse(Long categoryId) throws ResourceNotFoundException {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found for this id :: " + categoryId));
		
		this.categoryRepository.delete(category);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
	}
}
