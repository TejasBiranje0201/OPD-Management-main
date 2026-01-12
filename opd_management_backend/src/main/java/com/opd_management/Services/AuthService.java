package com.opd_management.Services;

import com.opd_management.dtos.LoginReq_Dto;
import com.opd_management.responce.JWtResponse;


public interface AuthService {
	
	JWtResponse doctorLogin(LoginReq_Dto loginReq_Dto );
	JWtResponse adminLogin(LoginReq_Dto loginReq_Dto );
	JWtResponse recptionLogin(LoginReq_Dto loginReq_Dto );

}
