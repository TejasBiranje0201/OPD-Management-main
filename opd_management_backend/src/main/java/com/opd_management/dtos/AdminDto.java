package com.opd_management.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AdminDto {

	
	@NotBlank(message = "name is required")
	@Size(min = 3,max = 50 ,message = "Name must be between 3–50 characters")
	private String name;
	
	@NotBlank(message = "Email is Required")
	@Email(message = "Enter Valid Email address")
	private String email;
	
	@NotBlank(message = "Password cannot be blank")
    @Size(min = 6, message = "Password must be at least 6 characters")
	private String password;
	
	@NotBlank(message = "Mobile number is required")
	@Pattern(regexp="^[0-9]{10}$",message = "Mobile number must be exactly 10 digits")
	private String mobileno;
	
	private String token;
	@PastOrPresent(message = "Creating date must be past or present")
    private LocalDateTime created_at;
	@PastOrPresent(message = "Creating date must be past or present")
    private LocalDateTime updated_at;
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
		this.created_at = LocalDateTime.now();
	}
	public LocalDateTime getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = LocalDateTime.now();
	}
	@Override
	public String toString() {
		return "adminDto [name=" + name + ", email=" + email + ", password=" + password + ", mobileno=" + mobileno
				+ ", token=" + token + ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
	}
	public AdminDto(
			@NotBlank(message = "name is required") @Size(min = 3, max = 50, message = "Name must be between 3–50 characters") String name,
			@NotBlank(message = "Email is Required") @Email(message = "Enter Valid Email address") String email,
			@NotBlank(message = "Password cannot be blank") @Size(min = 6, message = "Password must be at least 6 characters") String password,
			@NotBlank(message = "Mobile number is required") @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be exactly 10 digits") String mobileno,
			String token, LocalDateTime created_at, LocalDateTime updated_at) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobileno = mobileno;
		this.token = token;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	public AdminDto() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
	
}
