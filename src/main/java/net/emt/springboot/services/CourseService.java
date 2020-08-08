package net.emt.springboot.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import net.emt.springboot.exception.ResourceNotFoundException;
import net.emt.springboot.model.Course;
import net.emt.springboot.repository.CourseRepository;

@Component
public class CourseService {

	@Autowired CourseRepository courseRepository;
	
	public List<Course> getAllCourses() {
		return this.courseRepository.findAll();
	}
	
	public ResponseEntity<Course> getCourseById(Long courseId) throws ResourceNotFoundException {
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found for this id ::" + courseId));	
		return ResponseEntity.ok().body(course);
	}
	
	public List<Course> getCourseByCategory(Long courseCategory) {
		List<Course> courses = new ArrayList<>();
		courseRepository.findByCategoryId(courseCategory)
		.forEach(courses::add);
		return courses;
	}
	
	public Course createCourse(Course course) {
		return this.courseRepository.save(course);
	}
	
	public ResponseEntity<Course> updateCourse(Long courseId, Course courseDetails) throws ResourceNotFoundException {
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found for this id :: " + courseId));
		course.setCourseName(courseDetails.getCourseName());		
		return ResponseEntity.ok(this.courseRepository.save(course));
	}
	
	public Map<String, Boolean> deleteCourse(Long courseId) throws ResourceNotFoundException {
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found for this id :: " + courseId));
		
		this.courseRepository.delete(course);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
	}
}
