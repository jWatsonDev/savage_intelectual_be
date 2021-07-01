package com.si.springboot.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.si.springboot.rest.model.User;
import com.si.springboot.rest.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public ResponseEntity<User> saveUser(User user) {
		/*
		 * TODO
		 * Protect against update. Only new users allowed. The user should not have an id.
		 * Check username for uniqueness
		 */
		
		// Encrypt password
		String password = user.getPassword();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(password));
		
		User savedUser = userRepository.save(user);
		user.setPassword(password);
		return new ResponseEntity<User>(savedUser, HttpStatus.OK);
	}
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	

}
