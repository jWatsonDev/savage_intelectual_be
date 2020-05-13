package com.jwtauthregistrationexample.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwtauthregistrationexample.springboot.model.Example;

@Repository
public interface ExampleRepository extends JpaRepository<Example, Long> {

}
