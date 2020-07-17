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
import net.emt.springboot.repository.QuestionRepository;

@RestController
@RequestMapping("/api/v1/")
public class QuestionController {

	@Autowired
	private QuestionRepository questionRepository;
	
	@GetMapping("questions")
	public List<Question> getAllQuestions() {
		return this.questionRepository.findAll();
	}
	
	@GetMapping("/questions/{id}")
	public ResponseEntity<Question> getQuestionById(@PathVariable(value = "id") Long questionId) throws ResourceNotFoundException {
			Question question = questionRepository.findById(questionId)
					.orElseThrow(() -> new ResourceNotFoundException("Question not found for this id ::" + questionId));	
	return ResponseEntity.ok().body(question);
	}
	
	@PostMapping("questions")
	public Question createQuestion(@RequestBody Question question) {
		return this.questionRepository.save(question);
	}
	
	@PutMapping("questions/{id}")
	public ResponseEntity<Question> updateQuestion(@PathVariable(value = "id") Long questionId,
			@Valid @RequestBody Question questionDetails) throws ResourceNotFoundException {
		Question question = questionRepository.findById(questionId)
				.orElseThrow(() -> new ResourceNotFoundException("Question not found for this id :: " + questionId));
		
		question.setQuestion(questionDetails.getQuestion());
		question.setAnswerA(questionDetails.getAnswerA());
		question.setAnswerB(questionDetails.getAnswerB());
		question.setAnswerC(questionDetails.getAnswerC());
		question.setAnswerD(questionDetails.getAnswerD());
		question.setCorrectAnswer(questionDetails.getCorrectAnswer());
		question.setCourseId(questionDetails.getCourseId());
		
		return ResponseEntity.ok(this.questionRepository.save(question));
	}
	
	@DeleteMapping("question/{id}")
	public Map<String, Boolean> deleteQuestion(@PathVariable(value = "id") Long questionId) throws ResourceNotFoundException {
		Question question = questionRepository.findById(questionId)
				.orElseThrow(() -> new ResourceNotFoundException("Question not found for this id :: " + questionId));
		
		this.questionRepository.delete(question);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
	}
	
}
