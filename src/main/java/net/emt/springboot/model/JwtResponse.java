package net.emt.springboot.model;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class JwtResponse {

	private String jwt;
	private String type = "Bearer";
	private Integer id;
	private String username;
	private String password;

	private List<GrantedAuthority> roles;
	
	public JwtResponse(String jwt,  String username, List<GrantedAuthority> roles) {
		super();
		this.jwt = jwt;
		this.username = username;
		this.roles = roles;
	}
	

	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public List<GrantedAuthority> getRoles() {
		return roles;
	}
	
	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
