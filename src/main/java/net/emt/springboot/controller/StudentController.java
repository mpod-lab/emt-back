package net.emt.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.emt.springboot.exception.ResourceNotFoundException;
import net.emt.springboot.model.Student;
import net.emt.springboot.repository.StudentRepository;

@RestController
@RequestMapping("/api")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping("/students/")
	public List<Student> getAllStudents() {
		return this.studentRepository.findAll();
	}
	
	@PostMapping("/students/")
	public Student addNewStudent(@RequestBody Student student) throws ResourceNotFoundException{
		return this.studentRepository.save(student);
	}
}
