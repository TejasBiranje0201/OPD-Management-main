package com.opd_management.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opd_management.entities.Admin;
import com.opd_management.entities.Doctor;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
	Boolean existsByEmail(String email);
	Optional<Admin>  findByEmail(String email);
	
	

}
