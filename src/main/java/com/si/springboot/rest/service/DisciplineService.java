package com.si.springboot.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.si.springboot.rest.model.Discipline;
import com.si.springboot.rest.repository.DisciplineRepository;

@Service
public class DisciplineService {
	
	@Autowired
	DisciplineRepository disciplineRepository;
	
	public List<Discipline> getAllDisciplines() {
		return disciplineRepository.findAll();
	}

	public ResponseEntity<Discipline> saveDiscipline(Discipline discipline) {
		Discipline savedDiscipline = disciplineRepository.save(discipline);
		return new ResponseEntity<Discipline>(savedDiscipline, HttpStatus.OK);
	}

	public List<Discipline> getAllDisciplinesByUsername(String username) {
		return disciplineRepository.findByUsername(username);
	}

	public ResponseEntity<Void> deleteDiscipline(Long id) {
		disciplineRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
