package com.si.springboot.rest.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import com.si.springboot.rest.dto.Progress;
import com.si.springboot.rest.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

	@Query("from Status s where s.username = :username and DATE(s.date) = :date")
	public List<Status> findByUsernameAndDate(String username, @DateTimeFormat Date date);
	
	@Query("select new com.si.springboot.rest.dto.Progress(s.id, s.complete, s.date, s.username, d.id, d.name) "
			+ "from Status s "
			+ "join Discipline d on d.id = s.disciplineId "
			+ "where s.username = :username "
			+ "and d.id = :disciplineId "
			+ "order by s.date")
	public List<Progress> getProgressByUsernameAndDisciplineId(String username, Long disciplineId);
	
	public List<Status> findByUsername(String username);
}
