package com.yooyoo.service;

import com.yooyoo.model.CredManager;
import com.yooyoo.vo.LoginVO;
import com.yooyoo.vo.Profile;
import com.yooyoo.vo.ResultVO;

public interface LoginService {

	public CredManager authenticate(CredManager credManager);
	
	public Profile authenticateMobileUser(LoginVO logInVO);
	
	ResultVO sendForgotPasswordEmail(LoginVO logInVO);
}
