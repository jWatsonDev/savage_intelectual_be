package com.si.springboot.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.si.springboot.rest.model.Example;
import com.si.springboot.rest.repository.ExampleRepository;

@Service
public class ExampleService {
	
	@Autowired
	private ExampleRepository exampleRepository;
	
//	private static List<Example> exampleList = new ArrayList<Example>();
//	private static int exampleCounter = 0; 
//	
//	static {
//		exampleList.add(new Example((long) ++exampleCounter, "An example worth pondering..."));
//		exampleList.add(new Example((long) ++exampleCounter, "An example worth discussing..."));
//		exampleList.add(new Example((long) ++exampleCounter, "An example worth following..."));
//	}
	
	public List<Example> getAllExamples() {
		return exampleRepository.findAll();
	}

	public Example getExampleById(Long id) {
		return exampleRepository.findById(id).get();
	}
	
	public ResponseEntity<Void> deleteExample(Long id) {
		exampleRepository.deleteById(id);
		// will return if delete successful
		return ResponseEntity.noContent().build();
	}
	
	public ResponseEntity<Example> saveExample(Example example) {
		Example savedExample = exampleRepository.save(example);
		return new ResponseEntity<Example>(savedExample, HttpStatus.OK);
	}
	
	

}
