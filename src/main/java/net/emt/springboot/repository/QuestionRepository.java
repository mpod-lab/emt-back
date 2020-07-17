package net.emt.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.emt.springboot.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
