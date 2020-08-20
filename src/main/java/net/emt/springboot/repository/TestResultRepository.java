package net.emt.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.emt.springboot.model.TestResult;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {
	public List<TestResult> findByCourseId(Long courseId);
	public List<TestResult> findByTrainerId(Long trainerId);
	public List<TestResult> findByCourseIdAndTrainerId(long courseId, long trainerId);
}
