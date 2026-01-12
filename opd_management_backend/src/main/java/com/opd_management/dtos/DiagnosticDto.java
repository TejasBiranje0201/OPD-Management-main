package com.opd_management.dtos;

import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class DiagnosticDto {
	
	@NotBlank(message = "Diagnostic name is required")
	private String name;

	@NotNull(message = "Created date is required")
	private LocalDateTime created_at;

	@Positive(message = "Doctor ID must be a positive integer")
	@NotNull(message = "Doctorid must be required")
	private int doctorid;

	@NotNull(message = "visitid must be required")
	@Positive(message = "Visit ID must be a positive integer")
	private int visitid;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = LocalDateTime.now();
	}
	public int getDoctorid() {
		return doctorid;
	}
	public void setDoctorid(int doctorid) {
		this.doctorid = doctorid;
	}
	public int getVisitid() {
		return visitid;
	}
	public void setVisitid(int visitid) {
		this.visitid = visitid;
	}
	
	
}
