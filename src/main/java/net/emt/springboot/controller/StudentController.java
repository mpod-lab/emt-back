package net.emt.springboot.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.emt.springboot.exception.ResourceNotFoundException;
import net.emt.springboot.model.Category;
import net.emt.springboot.model.Student;
import net.emt.springboot.repository.StudentRepository;
import net.emt.springboot.services.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@GetMapping("/students/")
	public List<Student> getAllStudents() {
		return this.studentService.getAllStudents();
	}
	
	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") Long studentId) throws ResourceNotFoundException {
		return this.studentService.getStudentById(studentId);
	}
	
	@PostMapping("/students/")
	public Student addNewStudent(@RequestBody Student student) throws ResourceNotFoundException{
		return this.studentService.addNewStudent(student);
	}
	
	@DeleteMapping("/admin/students/{id}")
	public  Map<String, Boolean> deleteStudent(@PathVariable(value = "id") Long studentId) throws ResourceNotFoundException{
		return this.studentService.deleteStudent(studentId);
	}
}
