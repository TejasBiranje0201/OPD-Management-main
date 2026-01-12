package com.opd_management.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Admin {
	

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String password;
	private String mobileno;
	@Enumerated(EnumType.STRING)
	private Role role;   // ADMIN / DOCTOR/ RECEPTIONIST
	private String token;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = LocalDateTime.now();;
	}
	public LocalDateTime getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = LocalDateTime.now();;
	}
	@Override
	public String toString() {
		return "admin [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", mobileno="
				+ mobileno + ", role=" + role + ", token=" + token + ", created_at=" + created_at + ", updated_at="
				+ updated_at + "]";
	}
	public Admin(int id, String name, String email, String password, String mobileno, Role role, String token,
			LocalDateTime created_at, LocalDateTime updated_at) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobileno = mobileno;
		this.role = role;
		this.token = token;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
