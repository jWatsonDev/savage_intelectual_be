package com.si.springboot.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.si.springboot.rest.model.Example;
import com.si.springboot.rest.service.ExampleService;

@RestController
public class ExampleController {
	
	@Autowired
	private ExampleService exampleService;
	
	@GetMapping(path = "/example")
	public String getExample() {
		return "Example text.";
	}
	
	@GetMapping(path = "/example-bean")
	public Example getExampleBean() {
		return new Example(1L, "Example");
	}
	
	@GetMapping(path = "/examples")
	public List<Example> getAllExamples() {
		return exampleService.getAllExamples();
	}
	
	@GetMapping(path = "/user/{userName}/examples")
	public List<Example> getAllExamplesByUser(@PathVariable String userName) {
		return exampleService.getAllExamples();
	}
	
	@GetMapping(path = "/examples/{id}")
	public Example getExampleById(@PathVariable Long id) {
		return exampleService.getExampleById(id);
	}
	
	@DeleteMapping(path = "/examples/{id}")
	public ResponseEntity<Void> deleteExample(@PathVariable Long id) {
		return exampleService.deleteExample(id);
	}
	
	@PutMapping(path = "/examples")
	public ResponseEntity<Example> updateExample(@RequestBody Example example) {
		return exampleService.saveExample(example);
	}
	
	@PostMapping(path = "/examples")
	public ResponseEntity<Example> createExample(@RequestBody Example example) {
		return exampleService.saveExample(example);
	}
	
}
