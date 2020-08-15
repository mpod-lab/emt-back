package net.emt.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

import net.emt.springboot.repository.CategoryRepository;
import net.emt.springboot.repository.TestResultRepository;
import net.emt.springboot.exception.ResourceNotFoundException;
import net.emt.springboot.model.Category;
import net.emt.springboot.model.TestResult;

@Component
public class TestResultService {

	@Autowired TestResultRepository testResultRepository;
	@Autowired CategoryRepository categoryRepository;
	
	public List<TestResult> getAllTestResults(){
		return this.testResultRepository.findAll();
	}
	
	public TestResult saveTestResult(TestResult testResult) throws ResourceNotFoundException{
		if (testResult != null && testResult.getCategory() != null && testResult.getCategory().getId() != null) {
			Category category = categoryRepository.findById(testResult.getCategory().getId())
					.orElseThrow(() -> new ResourceNotFoundException("Category not found for this id ::" + testResult.getCategory().getId()));	
			testResult.setCategory(category);
		}
		
		return this.testResultRepository.save(testResult);
	}
}
