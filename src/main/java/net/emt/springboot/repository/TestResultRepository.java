package net.emt.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.emt.springboot.model.TestResult;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {

}
