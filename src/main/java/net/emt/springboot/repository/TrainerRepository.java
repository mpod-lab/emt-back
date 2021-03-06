package net.emt.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.emt.springboot.model.Trainer;

@Repository
public interface TrainerRepository  extends JpaRepository<Trainer, Long>{

}
