package com.opd_management.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.opd_management.Repositories.AdminRepository;
import com.opd_management.Repositories.DoctorRepository;
import com.opd_management.Repositories.ReceptionRepository;
import com.opd_management.Services.AuthService;
import com.opd_management.dtos.LoginReq_Dto;
import com.opd_management.entities.Admin;
import com.opd_management.entities.Doctor;
import com.opd_management.entities.Reception;
import com.opd_management.exception.ResourceNotFoundException;
import com.opd_management.responce.AdminResponse;
import com.opd_management.responce.DoctoreResponce;
import com.opd_management.responce.JWtResponse;
import com.opd_management.responce.ReceptionResponse;
import com.opd_management.security.JwtUtil;
@Service
public class AuthServiceImpl implements AuthService{
	
	
	// Inject JPA layer to perform doctor-related operations
	@Autowired
	private DoctorRepository doctorRepository;
	
	// Inject JPA layer to perform admin-related operations
	@Autowired
	private AdminRepository adminRepository;
	
	// Inject JPA layer to perform reception-related operations
	@Autowired
	private ReceptionRepository receptionRepository;
	
	// Inject security layer to  decoded pass & verify.
	@Autowired
	private PasswordEncoder passwordEncoder;

	  @Autowired
	    private JwtUtil jwtUtil;

	  
		@Override
	    public JWtResponse doctorLogin(LoginReq_Dto loginReq_Dto) {
			
	        Doctor doctor = doctorRepository.findByEmail(loginReq_Dto.getEmail())
	               .orElseThrow(() -> new ResourceNotFoundException("Email not found"));
	        if (!passwordEncoder.matches(loginReq_Dto.getPassword(), doctor.getPassword())) {
	            throw new RuntimeException("Invalid password");
	        }
	        
	        DoctoreResponce responce = new DoctoreResponce();
	        responce.setId(doctor.getId());
	        responce.setName(doctor.getName());
	        responce.setEmail(doctor.getEmail());
	        responce.setAddress(doctor.getAddress());
	        responce.setClinic_name(doctor.getClinic_name());
	        responce.setMobileno(doctor.getMobileno());
	        responce.setQualification(doctor.getQualification());
	        responce.setSpecialization(doctor.getSpecialization());
	        
	        // Generate JWT token
	        String token = jwtUtil.generateToken(doctor.getEmail(),doctor.getRole());
	      
	        return new JWtResponse(token,doctor.getRole(),responce);
	    }

		@Override
		public JWtResponse adminLogin(LoginReq_Dto loginReq_Dto) {
			Admin admin = adminRepository.findByEmail(loginReq_Dto.getEmail())
					.orElseThrow(() -> new ResourceNotFoundException("Email not found"));
			
			if(!passwordEncoder.matches(loginReq_Dto.getPassword(), admin.getPassword())) {
				throw new RuntimeException("Invalid password");
			}
			
			  // Generate JWT token
	        String token = jwtUtil.generateToken(admin.getEmail(),admin.getRole());

			AdminResponse response = new AdminResponse();
			response.setId(admin.getId());
			response.setEmail(admin.getEmail());
			response.setName(admin.getName());
			response.setMobileno(admin.getMobileno());
			
			
	        return new JWtResponse(token, admin.getRole(), response);
		
		}

		@Override
		public JWtResponse recptionLogin(LoginReq_Dto loginReq_Dto) {
			Reception reception = receptionRepository.findByEmail(loginReq_Dto.getEmail());
			if(!passwordEncoder.matches(loginReq_Dto.getPassword(), reception.getPassword())) {
				throw new RuntimeException("Invalid password");
			}
			ReceptionResponse response = new ReceptionResponse();
			response.setId(reception.getId());
			
			response.setDoctor_id(reception.getDoctorid().getId());
			response.setEmail(reception.getEmail());
			response.setName(reception.getName());
			response.setPhoneNo(reception.getPhoneNo());
			response.setShift(reception.getShift());
			
			 // Generate JWT token
	        String token = jwtUtil.generateToken(reception.getEmail(), reception.getRole());

	        return new JWtResponse(token, reception.getRole(), response);
		
			
			
		}

}
