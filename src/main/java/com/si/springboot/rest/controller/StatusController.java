package com.si.springboot.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.si.springboot.rest.dto.Progress;
import com.si.springboot.rest.dto.Streak;
import com.si.springboot.rest.model.Status;
import com.si.springboot.rest.service.StatusService;

@RestController
@CrossOrigin(origins = "*")
public class StatusController {
	
	@Autowired
	private StatusService statusService;
	
	@PostMapping(path = "/statuses")
	public ResponseEntity<Status> createStatus(@RequestBody Status status) {
		return statusService.saveStatus(status);
	}
	
	@GetMapping(path = "/statuses/users/{username}/date/{date}")
	public List<Status> getStatusByUsernameAndDate(@PathVariable String username, @PathVariable String date) {
		return statusService.getStatusByUsernameAndDate(username, date);
	}
	
	@GetMapping(path = "/progresses/users/{username}/disciplineId/{disciplineId}")
	public List<Progress> getProgressByUsernameAndDisciplineId(@PathVariable String username, @PathVariable Long disciplineId) {
		return statusService.getProgressByUsernameAndDisciplineId(username, disciplineId);
	}
	
	@GetMapping(path = "/streak/users/{username}/disciplineId/{disciplineId}")
	public Streak getStreakByUsernameAndDisciplineId(@PathVariable String username, @PathVariable Long disciplineId) {
		return statusService.getStreakByUsernameAndDisciplineId(username, disciplineId);
	}

}
