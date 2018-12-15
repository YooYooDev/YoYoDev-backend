package com.yooyoo.controller;

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
	
	 @RequestMapping(value = "/login", method = RequestMethod.POST)
	    public CredManager login(@ModelAttribute("userForm") CredManager userForm,  Model model) {
         CredManager user = loginService.authenticate(userForm);
	     return user;  
	    }
	 
	 //test method
	 @RequestMapping(value = "/test", method = RequestMethod.GET)
	    public String registration() {
	     return "hello";  
	    }


}
