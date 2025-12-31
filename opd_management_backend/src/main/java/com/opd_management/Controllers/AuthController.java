package com.opd_management.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opd_management.Services.AuthService;
import com.opd_management.dtos.LoginReq_Dto;
import com.opd_management.responce.JWtResponse;
import com.opd_management.responce.LoginResponce;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	
	// Inject service layer to perform Auth-related operations
	@Autowired
	private AuthService authService;

	 @PostMapping("/login")
	    public JWtResponse login(@Valid @RequestBody LoginReq_Dto request) {
	        return authService.login(request);
	    }
	
	
}
