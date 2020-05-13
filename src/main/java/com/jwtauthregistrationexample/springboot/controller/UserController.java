package com.jwtauthregistrationexample.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwtauthregistrationexample.springboot.model.User;
import com.jwtauthregistrationexample.springboot.service.UserService;

@RestController
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
