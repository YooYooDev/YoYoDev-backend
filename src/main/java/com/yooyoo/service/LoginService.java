package com.yooyoo.service;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import com.yooyoo.model.CredManager;
import com.yooyoo.vo.LoginVO;
import com.yooyoo.vo.Profile;
import com.yooyoo.vo.ResultVO;

public interface LoginService {

	public CredManager authenticate(CredManager credManager);
	
	public Profile authenticateMobileUser(LoginVO logInVO);
	
	ResultVO sendForgotPasswordEmail(LoginVO logInVO);
	
	Profile authenticateB2CMobileUser(LoginVO loginVO);

	public boolean sendOtpForB2C(LoginVO userForm);
	
	public String sendOtpForB2CForReg(String  mobile, String email) throws MalformedURLException, UnsupportedEncodingException;
}
