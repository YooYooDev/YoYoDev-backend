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
import com.yooyoo.vo.Profile;
import com.yooyoo.vo.SchoolInfo;
import com.yooyoo.vo.UserInfo;

@RestController
public class LoginController {
	@Autowired
	LoginService loginService;
	Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Profile login(@ModelAttribute("userForm") CredManager userForm,  Model model) {
        CredManager user = loginService.authenticate(userForm);
        logger.info("UserDetailas :- "+user);
        Profile profile = getProfile(user);
	     return profile;  
	}

	// testing purpose
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String registration() {

		return "hello";
	}
	
	
	 public Profile getProfile(CredManager user){
		 Profile p = new Profile();
		 p.setAccessToken("test_token");
		 UserInfo userInfo = new UserInfo();
		 userInfo.setEmail(user.getEmail());
		 userInfo.setRole(user.getRole().getName());
		 userInfo.setUser_name(user.getUser_name());
		 SchoolInfo school = new SchoolInfo();
		 school.setName(user.getSchool().getName());
		 school.setEmail_id(user.getSchool().getEmail_id());
		 school.setOwner_name(user.getSchool().getOwner_name());
		 school.setEnable_attendance(user.getSchool().getEnable_attendance());
		 school.setEnable_fees(user.getSchool().getEnable_fees());
		 school.setEnable_printed_worksheet(user.getSchool().getEnable_attendance());
		 userInfo.setSchool(school);
		 p.setUserInfo(userInfo);
		 return p;
	 }

}

