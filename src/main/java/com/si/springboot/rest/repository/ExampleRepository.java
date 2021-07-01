package com.si.springboot.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.si.springboot.rest.model.Example;

@Repository
public interface ExampleRepository extends JpaRepository<Example, Long> {

}
