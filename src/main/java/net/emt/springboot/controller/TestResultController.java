package net.emt.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.emt.springboot.exception.ResourceNotFoundException;
import net.emt.springboot.model.ResultFromCourseAndTrainerRequestBody;
import net.emt.springboot.model.TestResult;
import net.emt.springboot.services.TestResultService;


@RestController
@RequestMapping("/api")
public class TestResultController {

	@Autowired TestResultService testResultService;
	
	@GetMapping("/testResult/")
	public List<TestResult> getAllTestResults(){
		return this.testResultService.getAllTestResults();
	}
	
	@PostMapping("/testResult/")
	public TestResult saveTestResult(@RequestBody TestResult testResult) throws ResourceNotFoundException{
		return this.testResultService.saveTestResult(testResult);
	}
  
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping("/resultFromSpecificCourse/{id}")
	public ResponseEntity<Integer> getResultFromSpecificCourse(@PathVariable(value = "id") Long courseId) {
		return this.testResultService.getResultFromSpecificCourse(courseId);
	}
   
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
	@PostMapping("/resultFromCourseAndTrainer")
	public List<TestResult> resultFromCourseAndTrainer(@RequestBody ResultFromCourseAndTrainerRequestBody testResult) throws ResourceNotFoundException{
		return this.testResultService.resultFromCourseAndTrainer(testResult);
	}
  
	@GetMapping("/testResult/{courseId}")
	public List<TestResult> getTestByCourseId(@PathVariable(value = "courseId") Long courseId) {
		return this.testResultService.getTestByCourseId(courseId);
	}
}
