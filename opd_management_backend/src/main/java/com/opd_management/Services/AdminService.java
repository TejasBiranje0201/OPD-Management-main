package com.opd_management.Services;

import java.util.List;

import com.opd_management.entities.Admin;

public interface AdminService {
	
		Admin saveAdmin(Admin admin);
		Admin getAdminById(int id);
		List<Admin> getAllAdmins();
		void deleteAdmin(int id);
		Admin getAdminByEmail(String email);
		

}
