package net.emt.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import net.emt.springboot.repository.CategoryRepository;
import net.emt.springboot.repository.CourseRepository;
import net.emt.springboot.repository.TestResultRepository;
import net.emt.springboot.exception.ResourceNotFoundException;
import net.emt.springboot.model.Category;
import net.emt.springboot.model.Course;
import net.emt.springboot.model.TestResult;

@Component
public class TestResultService {

	@Autowired TestResultRepository testResultRepository;
	@Autowired CategoryRepository categoryRepository;
	@Autowired CourseRepository courseRepository;

	
	public List<TestResult> getAllTestResults(){
		return this.testResultRepository.findAll();
	}
	
	public TestResult saveTestResult(TestResult testResult) throws ResourceNotFoundException{
		if (testResult != null && testResult.getCategory() != null && testResult.getCategory().getId() != null) {
			Category category = categoryRepository.findById(testResult.getCategory().getId())
					.orElseThrow(() -> new ResourceNotFoundException("Category not found for this id ::" + testResult.getCategory().getId()));	
			testResult.setCategory(category);
			Float res = calculateResult(testResult.getScore(), testResult.getAllQuestions());
			testResult.setResult(res);
		}
		if (testResult != null && testResult.getCourse() != null) {
			Course course = courseRepository.findById(testResult.getCourse().getId())
					.orElseThrow(() -> new ResourceNotFoundException("Course not found for this id ::" + testResult.getCourse().getId()));	
			testResult.setCourse(course);
		}
		
		return this.testResultRepository.save(testResult);
	}
	
	public List<TestResult> getTestByCourseId(Long courseId){
		List<TestResult> results = new ArrayList<>();
		testResultRepository.findByCourseId(courseId)
		.forEach(results::add);
		return results;
	}
	
	public ResponseEntity<Integer> getResultFromSpecificCourse(Long courseId) {
		int total = 0;
		if (courseId != null) {
			List<TestResult> testResults = new ArrayList<>();
			testResultRepository.findByCourseId(courseId)
			.forEach(testResults::add);
			
			float result = 0;
			 for(TestResult res:testResults)  {
			        result += res.getResult(); 
			 }  
				if (result > 0) {
					total = (int)result/testResults.size();
				}
		}
		return ResponseEntity.ok().body(total);
	}
	
	public Float calculateResult(Long score, Long allQuestions) {
		Float result = 0.00F;
		if (score != null && allQuestions != null) {
			result = ((float)score / allQuestions) * 100;
		}
		return result;
	}
}
