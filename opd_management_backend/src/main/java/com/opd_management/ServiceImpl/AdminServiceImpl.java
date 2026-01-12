package com.opd_management.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opd_management.Repositories.AdminRepository;
import com.opd_management.Services.AdminService;
import com.opd_management.entities.Admin;
import com.opd_management.exception.DatabaceException;
import com.opd_management.exception.DuplicateValuesException;
import com.opd_management.exception.ResourceNotFoundException;
@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Admin saveAdmin(Admin admin) {
		try {

			if (adminRepository.existsByEmail(admin.getEmail())) {
				throw new DuplicateValuesException("doctor with email " + admin.getEmail() + " already exists");
			}
			return adminRepository.save(admin);// repository method
		} catch (Exception e) {
			throw new DatabaceException("Failed to save doctor due to database error");
		}
	}

	@Override
	public Admin getAdminById(int id) {
		// TODO Auto-generated method stub
		return adminRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Admin not found with id: " + id));
	}

	@Override
	public List<Admin> getAllAdmins() {
		try {
			return adminRepository.findAll();// repository method
		} catch (Exception e) {
			throw new DatabaceException("Failed to get list of  Admin  due to database error");
		}
	}

	@Override
	public void deleteAdmin(int id) {
		try {
			try {
				adminRepository.deleteById(id);// repository method
			} catch (Exception e) {
				throw new ResourceNotFoundException("Admin not found with id: " + id);
			}
		} catch (Exception e) {
			throw new DatabaceException("Failed to delete Admin due to database error");
		}
		
	}

	@Override
	public Admin getAdminByEmail(String email) {
		try {
			return adminRepository.findByEmail(email)//  custom repository method
					.orElseThrow(() -> new ResourceNotFoundException(
	                "Doctor not found with email: " + email));
		} catch (Exception e) {
			throw new DatabaceException("Failed to get doctor due to database error");
		}
			}
	}
	


