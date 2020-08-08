package net.emt.springboot.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import net.emt.springboot.exception.ResourceNotFoundException;
import net.emt.springboot.model.Question;
import net.emt.springboot.repository.QuestionRepository;

@Component
public class QuestionService {

	@Autowired QuestionRepository questionRepository;
	
	public List<Question> getAllQuestions() {
		return this.questionRepository.findAll();
	}
	
	public ResponseEntity<Question> getQuestionById(Long questionId) throws ResourceNotFoundException {
		Question question = questionRepository.findById(questionId)
				.orElseThrow(() -> new ResourceNotFoundException("Question not found for this id ::" + questionId));	
		return ResponseEntity.ok().body(question);
	}
	
	public Question createQuestion(Question question) {
		return this.questionRepository.save(question);
	}
	
	public ResponseEntity<Question> updateQuestion(Long questionId, Question questionDetails) throws ResourceNotFoundException {
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
	
	public Map<String, Boolean> deleteQuestion(Long questionId) throws ResourceNotFoundException {
		Question question = questionRepository.findById(questionId)
				.orElseThrow(() -> new ResourceNotFoundException("Question not found for this id :: " + questionId));
		
		this.questionRepository.delete(question);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
	}
}
