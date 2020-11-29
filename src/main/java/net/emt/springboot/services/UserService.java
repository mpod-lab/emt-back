package net.emt.springboot.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.emt.springboot.model.User;
import net.emt.springboot.repository.UserRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Service("userService")
public class UserService implements UserDetailsService {

	@Autowired UserRepository userRepository;
	
	public ResponseEntity<User> login(User userData){
		 User user = userRepository.findByUsername(userData.getUsername());
	        if(user == null) {
	            throw new RuntimeException("User does not exist.");
	        }
	        if(!user.getPassword().equals(user.getPassword())){
	            throw new RuntimeException("Password mismatch.");
	        }
	        return  ResponseEntity.ok().body(user);
	}
	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new RuntimeException("User does not exist.");
        }
        if(!user.getPassword().equals(user.getPassword())){
            throw new RuntimeException("Password mismatch.");
        }
    	List<GrantedAuthority> authorities = new ArrayList<>();
    	GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
    	authorities.add(authority);
    	
    	return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);	
	}
}
