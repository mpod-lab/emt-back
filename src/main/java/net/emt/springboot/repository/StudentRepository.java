package net.emt.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.emt.springboot.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
