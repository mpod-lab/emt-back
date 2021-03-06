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
import net.emt.springboot.model.Course;
import net.emt.springboot.services.CourseService;

@RestController
@RequestMapping("/api/")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@GetMapping("courses")
	public List<Course> getAllCourses() {
		return this.courseService.getAllCourses();
	}
	
	@GetMapping("courses/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable(value = "id") Long courseId) throws ResourceNotFoundException {
		return this.courseService.getCourseById(courseId);
	}
	
	@GetMapping("course/{category}")
	public List<Course> getCourseByCategory(@PathVariable(value = "category") Long courseCategory) {
		return this.courseService.getCourseByCategory(courseCategory);
	}
	
	@PostMapping("admin/courses")
	public Course createCourse(@RequestBody Course course) throws ResourceNotFoundException{
		return this.courseService.createCourse(course);
	}
	
	@PutMapping("admin/course/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable(value = "id") Long courseId,
			@Valid @RequestBody Course courseDetails) throws ResourceNotFoundException {
		return this.courseService.updateCourse(courseId, courseDetails);
	}
	
	@DeleteMapping("admin/course/{id}")
	public Map<String, Boolean> deleteCourse(@PathVariable(value = "id") Long courseId) throws ResourceNotFoundException {
		return this.courseService.deleteCourse(courseId);
	}
}
