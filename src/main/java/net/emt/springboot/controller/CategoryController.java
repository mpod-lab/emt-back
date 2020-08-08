package net.emt.springboot.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.emt.springboot.exception.ResourceNotFoundException;
import net.emt.springboot.model.Category;
import net.emt.springboot.model.Course;
import net.emt.springboot.repository.CategoryRepository;

@RestController
@RequestMapping("/api/v1/")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("category")
	public List<Category> getAllCategories() {
		return this.categoryRepository.findAll();
	}
	
	@GetMapping("/category/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") Long categoryId) throws ResourceNotFoundException {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found for this id ::" + categoryId));	
		return ResponseEntity.ok().body(category);
	}
	
	@PostMapping("category")
	public Category createCategory(@RequestBody Category category) {
		return this.categoryRepository.save(category);
	}
	
	
	@PutMapping("/category/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable(value = "id") Long categoryId,
			@Valid @RequestBody Category categoryDetails) throws ResourceNotFoundException {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found for this id :: " + categoryId));
		
		category.setCategoryName(categoryDetails.getCategoryName());
		
		return ResponseEntity.ok(this.categoryRepository.save(category));
	}
	
	@DeleteMapping("/category/{id}")
	public Map<String, Boolean> deleteCourse(@PathVariable(value = "id") Long categoryId) throws ResourceNotFoundException {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found for this id :: " + categoryId));
		
		this.categoryRepository.delete(category);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
	}
}
