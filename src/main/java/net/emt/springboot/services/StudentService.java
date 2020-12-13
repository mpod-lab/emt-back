package net.emt.springboot.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import net.emt.springboot.exception.ResourceNotFoundException;
import net.emt.springboot.model.Student;
import net.emt.springboot.repository.StudentRepository;

@Component
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> getAllStudents() {
		return this.studentRepository.findAll();
	}
	
	public Student addNewStudent(Student student) {
		return this.studentRepository.save(student);
	}
	
	public ResponseEntity<Student> getStudentById(Long studentId) throws ResourceNotFoundException {
		Student student = this.studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));
		return ResponseEntity.ok().body(student);
	}
	
	public  Map<String, Boolean> deleteStudent(Long studentId) throws ResourceNotFoundException {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));
		
		this.studentRepository.delete(student);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
	}
}
