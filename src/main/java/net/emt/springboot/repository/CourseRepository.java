package net.emt.springboot.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.emt.springboot.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
