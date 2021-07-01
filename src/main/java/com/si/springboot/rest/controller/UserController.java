package com.si.springboot.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.si.springboot.rest.model.User;
import com.si.springboot.rest.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(path = "/users")
	public List<User> getExample() {
		return userService.getAllUsers();
	}
	
	@PostMapping(path = "/register")
	public ResponseEntity<User> createExample(@RequestBody User user) {
		return userService.saveUser(user);
	}

}
