package com.opd_management.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.opd_management.Repositories.DoctorRepository;
import com.opd_management.Services.DoctorService;
import com.opd_management.entities.Doctor;
import com.opd_management.exception.DatabaceException;
import com.opd_management.exception.DuplicateValuesException;
import com.opd_management.exception.ResourceNotFoundException;

@Service
public class DoctorServiceImpl implements DoctorService {

	// creating object of doctor repository
	@Autowired
	private DoctorRepository doctorRepository;

	// access repository methods
	@Override
	public Doctor saveDoctor(Doctor doctor) {
		try {

			if (doctorRepository.existsByEmail(doctor.getEmail())) {
				throw new DuplicateValuesException("doctor with email " + doctor.getEmail() + " already exists");
			}
			return doctorRepository.save(doctor);// repository method
		} catch (Exception e) {
			throw new DatabaceException("Failed to save doctor due to database error");
		}

	}

	@Override
	public List<Doctor> getAllDoctor() {
		try {
			return doctorRepository.findAll();// repository method
		} catch (Exception e) {
			throw new DatabaceException("Failed to get list of  doctor due to database error");
		}
		
	}

	@Override
	public Doctor getDoctorById(int id) {
//		try {
			return doctorRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));// repository
																											// method
//		} catch (Exception e) {
//			throw new DatabaceException("Failed to get   doctor due to database error");
//
//		}
	}

	@Override
	public void DeleteById(int id) {
		try {
			try {
				doctorRepository.deleteById(id);// repository method
			} catch (Exception e) {
				throw new ResourceNotFoundException("Doctor not found with id: " + id);
			}
		} catch (Exception e) {
			throw new DatabaceException("Failed to delete doctor due to database error");
		}
		
	}

	@Override
	public Doctor getDoctorByEmail(String email) {
		try {
			return doctorRepository.findByEmail(email)//  custom repository method
					.orElseThrow(() -> new ResourceNotFoundException(
	                "Doctor not found with email: " + email));
		} catch (Exception e) {
			throw new DatabaceException("Failed to get doctor due to database error");
		}
			}

}
