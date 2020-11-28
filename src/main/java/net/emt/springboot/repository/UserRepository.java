package net.emt.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.emt.springboot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByUsernameAndPassword(String username, String password);

	public User findByUsername(String username);
}
