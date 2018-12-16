package com.yooyoo.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yooyoo.controller.LoginController;
import com.yooyoo.model.CredManager;
import com.yooyoo.repository.CredManagerRepository;
import com.yooyoo.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	@Autowired
	CredManagerRepository credRepo;
	@Override
	public CredManager authenticate(CredManager credManager) {
		CredManager manager = credRepo.getUserDetails(credManager.getEmail(), credManager.getPassword());
		logger.info("Inside loginservice impl");
		/*credManager.setEmail("Test@email.com");
		Role role = new Role();
		role.setName("SUPERADMIN");
		credManager.setRole(role);*/
		return manager;
	}

}
