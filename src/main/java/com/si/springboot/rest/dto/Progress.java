package com.si.springboot.rest.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Progress {
	private Long statusId;
	private Boolean status;
	private Date statusDate;
	private String username;
	private Long disciplineId;
	private String disciplineName;
}
