package net.emt.springboot.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.emt.springboot.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	public List<Course> findByCategoryId(long categoryId);
}
