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
import net.emt.springboot.model.Question;
import net.emt.springboot.services.QuestionService;

@RestController
@RequestMapping("/api")
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@GetMapping("/questions/")
	public List<Question> getAllQuestions() {
		return this.questionService.getAllQuestions();
	}
	
	@GetMapping("/questions/{id}")
	public ResponseEntity<Question> getQuestionById(@PathVariable(value = "id") Long questionId) throws ResourceNotFoundException {
			return this.questionService.getQuestionById(questionId);
	}
	
	@PostMapping("/admin/questions/")
	public Question createQuestion(@RequestBody Question question) {
		return this.questionService.createQuestion(question);
	}
	
	@PutMapping("/admin/questions/{id}")
	public ResponseEntity<Question> updateQuestion(@PathVariable(value = "id") Long questionId,
			@Valid @RequestBody Question questionDetails) throws ResourceNotFoundException {
		return this.questionService.updateQuestion(questionId, questionDetails);
	}
	
	@DeleteMapping("/admin/questions/{id}")
	public Map<String, Boolean> deleteQuestion(@PathVariable(value = "id") Long questionId) throws ResourceNotFoundException {
		return this.questionService.deleteQuestion(questionId);
	}
	
}
