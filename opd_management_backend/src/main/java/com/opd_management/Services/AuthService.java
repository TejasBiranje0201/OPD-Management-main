package com.opd_management.Services;

import com.opd_management.dtos.LoginReq_Dto;
import com.opd_management.responce.JWtResponse;
import com.opd_management.responce.LoginResponce;

public interface AuthService {
	
	JWtResponse login(LoginReq_Dto loginReq_Dto );

}
