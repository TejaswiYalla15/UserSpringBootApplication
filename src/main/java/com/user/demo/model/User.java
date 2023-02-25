package com.user.demo.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	
	private String password;
	
	private String email_id;
	
	
	@OneToMany(cascade = CascadeType.ALL,
			   fetch = FetchType.LAZY,
			   mappedBy = "user")
	@JsonIgnore
	private Set<Image> user_images = new HashSet<>();

	public User() {
		
	}
	public User(Long id, String username, String password, String email_id, Set<Image> user_images) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email_id = email_id;
		this.user_images = user_images;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	
	public Set<Image> getUser_images() {
		return user_images;
	}
	public void setUser_images(Set<Image> user_images) {
		this.user_images = user_images;
	}
	
}
