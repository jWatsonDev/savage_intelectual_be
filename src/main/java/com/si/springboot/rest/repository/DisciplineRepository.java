package com.si.springboot.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.si.springboot.rest.model.Discipline;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Long> {
	
	public List<Discipline> findByUsername(String username);

}
