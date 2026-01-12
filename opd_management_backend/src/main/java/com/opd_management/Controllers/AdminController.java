package com.opd_management.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opd_management.Services.AdminService;
import com.opd_management.dtos.AdminDto;
import com.opd_management.entities.Admin;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/")
	public ResponseEntity<Admin> saveAdmin(@Valid @RequestBody AdminDto adminDto){
		Admin admin = new Admin();
		admin.setName(adminDto.getName());
		admin.setEmail(adminDto.getEmail());
		admin.setMobileno(adminDto.getMobileno());
		admin.setToken(adminDto.getToken());
		admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
		admin.setCreated_at(adminDto.getCreated_at());
		admin.setUpdated_at(adminDto.getUpdated_at());
		Admin saveadmin = adminService.saveAdmin(admin);
		return new ResponseEntity<>(saveadmin, HttpStatus.CREATED);
	}
	
	@GetMapping("/admin_list")
	public ResponseEntity<List<Admin>> getAllAdmins(){
		List<Admin> admin = adminService.getAllAdmins();
		if (admin==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(admin, HttpStatus.FOUND);
	}
	
	
	@GetMapping("/admin/{id}")
	public ResponseEntity<Admin> getAdmin(@PathVariable("id") int id){
		Admin admin = adminService.getAdminById(id);
		if (admin==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		}
		return new ResponseEntity<>(HttpStatus.FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAdmin(@PathVariable("id")int id){
		Admin admin = adminService.getAdminById(id);
		if (admin==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.MOVED_PERMANENTLY);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Admin> updateAdmin(@PathVariable("id")int id, @Valid@RequestBody AdminDto adminDto){
		Admin admin = adminService.getAdminById(id);
		if (admin==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		}
		admin.setName(adminDto.getName());
		admin.setEmail(adminDto.getEmail());
		admin.setMobileno(adminDto.getMobileno());
		admin.setToken(adminDto.getToken());
		admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
		admin.setUpdated_at(adminDto.getUpdated_at());
		Admin updateadmin = adminService.saveAdmin(admin);
		return new ResponseEntity<>(updateadmin, HttpStatus.CREATED);
	}

}
