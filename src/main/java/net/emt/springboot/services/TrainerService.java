package net.emt.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import net.emt.springboot.exception.ResourceNotFoundException;
import net.emt.springboot.model.Trainer;
import net.emt.springboot.repository.TrainerRepository;


@Component
public class TrainerService {

	@Autowired
	private TrainerRepository trainerRepository;
	
	public List<Trainer> getAllTrainers() {
		return this.trainerRepository.findAll();
	}
	
	public Trainer saveTrainer(@RequestBody Trainer trainer) {
		return this.trainerRepository.save(trainer);
	}
	
	public ResponseEntity<Trainer> getTrainerById(@RequestBody Long trainerId) throws ResourceNotFoundException {
		Trainer trainer = trainerRepository.findById(trainerId)
				.orElseThrow(() -> new ResourceNotFoundException("Course not found for this id ::" + trainerId));	
		return ResponseEntity.ok().body(trainer);
	}
	
}
