package com.yooyoo.serviceImpl;

import org.springframework.stereotype.Service;

import com.yooyoo.model.CredManager;
import com.yooyoo.model.Role;
import com.yooyoo.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Override
	public CredManager authenticate(CredManager credManager) {
		credManager.setEmail("Test@email.com");
		Role role = new Role();
		role.setName("SUPERADMIN");
		credManager.setRole(role);
		return credManager;
	}

}
