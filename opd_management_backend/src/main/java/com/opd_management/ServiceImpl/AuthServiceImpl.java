package com.opd_management.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.opd_management.Repositories.DoctorRepository;
import com.opd_management.Services.AuthService;
import com.opd_management.dtos.LoginReq_Dto;
import com.opd_management.entities.Doctor;
import com.opd_management.exception.ResourceNotFoundException;
import com.opd_management.responce.JWtResponse;
import com.opd_management.security.JwtUtil;
@Service
public class AuthServiceImpl implements AuthService{
	
	
	// Inject JPA layer to perform doctor-related operations
	@Autowired
	private DoctorRepository doctorRepository;
	
	// Inject security layer to  decoded pass & verify.
	@Autowired
	private PasswordEncoder passwordEncoder;

	  @Autowired
	    private JwtUtil jwtUtil;

	    @Override
	    public JWtResponse login(LoginReq_Dto loginReq_Dto) {

	        Doctor doctor = doctorRepository.findByEmail(loginReq_Dto.getEmail())
	                .orElseThrow(() -> new ResourceNotFoundException("Email not found"));

	        if (!passwordEncoder.matches(loginReq_Dto.getPassword(), doctor.getPassword())) {
	            throw new RuntimeException("Invalid password");
	        }

	        // Generate JWT token
	        String token = jwtUtil.generateToken(doctor.getEmail());

	        return new JWtResponse(token, doctor.getEmail());
	    }

}
