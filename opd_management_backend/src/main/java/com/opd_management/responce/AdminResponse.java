package com.opd_management.responce;

import com.opd_management.entities.Role;



public class AdminResponse {
	private int id;
	private String name;
	private String email;
	private String mobileno;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	@Override
	public String toString() {
		return "AdminResponse [id=" + id + ", name=" + name + ", email=" + email + ", mobileno=" + mobileno + "]";
	}
	public AdminResponse(int id, String name, String email, String mobileno) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobileno = mobileno;
	}
	public AdminResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	


	
	
}
