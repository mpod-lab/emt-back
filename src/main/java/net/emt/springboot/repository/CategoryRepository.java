package net.emt.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.emt.springboot.model.Category;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Long>{

}
