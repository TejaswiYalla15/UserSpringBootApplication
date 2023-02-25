package com.user.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.demo.exception.ResourceNotFoundException;
import com.user.demo.model.User;
import com.user.demo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	public User saveUser(User user) {
		return userRepo.save(user);
	}
	
	public User getUserDatabyId(Long id) throws ResourceNotFoundException {
		return userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User is not found with the id :: " + id));
	}
	
	public Optional<User> getUserDatabyUsername(String username) throws ResourceNotFoundException {
		return userRepo.findAllByusername(username);
	}
	
}
