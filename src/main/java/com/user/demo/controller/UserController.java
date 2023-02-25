package com.user.demo.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.demo.exception.ResourceNotFoundException;
import com.user.demo.model.User;
import com.user.demo.repository.UserRepository;
import com.user.demo.service.UserService;

@CrossOrigin
@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserService userService;

//	API to register the user to the database
	@PostMapping("/saveUsertodb")
	public User saveUsertoDB(@RequestBody User user) {
		return userService.saveUser(user);
	}
		
//	API to get all the users registered
	@GetMapping("/getAllUsers")
	public ArrayList<User> getAllUsers(){
		return userRepo.findAll();
	}
	
//	API to get a particular user based on Id
	@GetMapping("/getUserbyID/{id}")
	public User getUserbyId(@PathVariable("id") Long id) throws ResourceNotFoundException {
		return userService.getUserDatabyId(id);
	}
	
//	API to get a particular user based on username
	@GetMapping("/getUserbyUsername/{username}")
	public Optional<User> getUserbyUsername(@PathVariable("username") String username) throws ResourceNotFoundException {
		return userService.getUserDatabyUsername(username);
	}
	
}
