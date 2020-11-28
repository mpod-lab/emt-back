package net.emt.springboot.controller;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.emt.springboot.filters.JwtUtils;
import net.emt.springboot.model.JwtResponse;
import net.emt.springboot.model.LoginRequestBody;
import net.emt.springboot.repository.UserRepository;

import net.emt.springboot.services.UserService;

@RestController
@RequestMapping("")
public class UserResourse {
	
	@Autowired()
	JwtUtils jwtUtils;

	@Autowired()
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	
	@Autowired UserService userService;
	
//  public ResponseEntity<User> login(@RequestBody User userData){
//		return this.userService.login(userData);
//  }
//	
//	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestBody loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		User userDetails = (User) authentication.getPrincipal();	

		List<GrantedAuthority> authorities = userDetails.getAuthorities()
				.stream()
				  .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
				    .collect(Collectors.toList());
		
		return ResponseEntity.ok(new JwtResponse(jwt, loginRequest.getUsername(), authorities));
	}
	
}
