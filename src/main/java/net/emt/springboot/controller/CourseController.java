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
import net.emt.springboot.model.Course;
import net.emt.springboot.repository.CourseRepository;

@RestController
@RequestMapping("/api/v1/")
public class CourseController {

	@Autowired
	private CourseRepository courseRepository;
	
	@GetMapping("courses")
	public List<Course> getAllCourses() {
		return this.courseRepository.findAll();
	}
	
	@GetMapping("/courses/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable(value = "id") Long categoryId) throws ResourceNotFoundException {
		Course course = courseRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found for this id ::" + categoryId));	
		return ResponseEntity.ok().body(course);
	}
	
	@PostMapping("courses")
	public Course createCourse(@RequestBody Course course) {
		return this.courseRepository.save(course);
	}
	
	@PutMapping("course/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable(value = "id") Long categoryId,
			@Valid @RequestBody Course courseDetails) throws ResourceNotFoundException {
		Course course = courseRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found for this id :: " + categoryId));
		
		course.setCourseName(courseDetails.getCourseName());
		course.setCategoryId(courseDetails.getCategoryId());
		
		return ResponseEntity.ok(this.courseRepository.save(course));
	}
	
	@DeleteMapping("course/{id}")
	public Map<String, Boolean> deleteCourse(@PathVariable(value = "id") Long categoryId) throws ResourceNotFoundException {
		Course course = courseRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found for this id :: " + categoryId));
		
		this.courseRepository.delete(course);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
	}
}
