package net.emt.springboot.controller;
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
import net.emt.springboot.services.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/category/")
	public List<Category> getAllCategories() {
		return this.categoryService.getAllCategories();
	}
	
	@GetMapping("/category/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") Long categoryId) throws ResourceNotFoundException {
		return this.categoryService.getCategoryById(categoryId);
	}
	
	
	@PostMapping("/admin/category/")
	public Category createCategory(@RequestBody Category category) {
		return this.categoryService.saveCategory(category);
	}
	
   
	@PutMapping("/admin/category/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable(value = "id") Long categoryId,
			@Valid @RequestBody Category categoryDetails) throws ResourceNotFoundException {
		return this.categoryService.updateCategory(categoryId, categoryDetails);
	}
	
    
	@DeleteMapping("/admin/category/{id}")
	public Map<String, Boolean> deleteCourse(@PathVariable(value = "id") Long categoryId) throws ResourceNotFoundException {
		return this.categoryService.deleteCourse(categoryId);
	}
}
