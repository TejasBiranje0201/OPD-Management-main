package com.opd_management.responce;

import java.time.LocalDateTime;

public class ErrorResponce {
	
	
	private int StetusCode;
	private String massege;
	private LocalDateTime timestamp;
	
	
	
	
	
	
	
	public ErrorResponce(int stetusCode, String massege) {
		super();
		StetusCode = stetusCode;
		this.massege = massege;
		this.timestamp = LocalDateTime.now();
	}
	
	
	
	public ErrorResponce() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getStetusCode() {
		return StetusCode;
	}
	public void setStetusCode(int stetusCode) {
		StetusCode = stetusCode;
	}
	public String getMassege() {
		return massege;
	}
	public void setMassege(String massege) {
		this.massege = massege;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	
	
	
}
