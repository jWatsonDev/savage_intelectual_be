package com.si.springboot.rest.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Entity @ToString
public class Status {
	@Id @GeneratedValue
	private Long id;
	private Long disciplineId;
	// TODO db normalization
	// already linked via disciplines
	// For now, though, will get statuses based on username and date
	private String username;
	private Date date;
	private Boolean complete;
}
