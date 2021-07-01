package com.si.springboot.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.si.springboot.rest.model.Discipline;
import com.si.springboot.rest.service.DisciplineService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DisciplineController {
	
	@Autowired
	DisciplineService disciplineService;
	
	@GetMapping(path = "/disciplines")
	public List<Discipline> getAllDisciplines() {
		return disciplineService.getAllDisciplines();
	}
	
	@GetMapping(path = "/disciplines/users/{username}")
	public List<Discipline> getAllDisciplinesByUsername(@PathVariable String username) {
		return disciplineService.getAllDisciplinesByUsername(username);
	}
	
	@PostMapping(path = "/disciplines")
	public ResponseEntity<Discipline> createHabit(@RequestBody Discipline discipline) {
		return disciplineService.saveDiscipline(discipline);
	}
	
	@DeleteMapping(path = "/disciplines/delete/{id}")
	public ResponseEntity<Void> deleteDiscipline(@PathVariable Long id) {
		return disciplineService.deleteDiscipline(id);
	}

}
