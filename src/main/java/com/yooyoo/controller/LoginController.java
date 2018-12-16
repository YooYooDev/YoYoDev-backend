package com.yooyoo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yooyoo.model.CredManager;
import com.yooyoo.service.LoginService;

@RestController
public class LoginController {
	@Autowired
	LoginService loginService;
	Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public CredManager login(@ModelAttribute("userForm") CredManager userForm, Model model) {
		System.out.println("login method hit " + userForm.getEmail() + " " + userForm.getPassword());
		CredManager user = loginService.authenticate(userForm);
		if(null!=user)
		logger.info("UserDetailas :- " + user.getId());
		else
		logger.info("UserDetailas NULL :- ");
				
			
		return user;
	}

	// testing purpose
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String registration() {

		return "hello";
	}

}
