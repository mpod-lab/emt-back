package net.emt.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.emt.springboot.exception.ResourceNotFoundException;
import net.emt.springboot.model.Trainer;
import net.emt.springboot.services.TrainerService;


@RestController
@RequestMapping("/api/trainers")
public class TrainerController {

	@Autowired
	private TrainerService trainerService;
	
	@GetMapping("/")
	public List<Trainer> getAllCategories() {
		return this.trainerService.getAllTrainers();
	}
	
    @PostAuthorize("hasRole('ADMIN')")
	@PostMapping("/")
	public Trainer createTrainer(@RequestBody Trainer trainer) {
		return this.trainerService.saveTrainer(trainer);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <Trainer> getTrainerById(@PathVariable(value = "id") Long trainerId) throws ResourceNotFoundException{
		return this.trainerService.getTrainerById(trainerId);
	}
}
