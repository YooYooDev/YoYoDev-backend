package com.yooyoo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
	public Profile login(@RequestBody CredManager userForm) {
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
		 userInfo.setUserName(user.getUser_name());
		 SchoolInfo school = new SchoolInfo();
		 school.setName(user.getSchool().getName());
		 school.setEmailId(user.getSchool().getEmail_id());
		 school.setOwnerName(user.getSchool().getOwner_name());
		 school.setEnableAttendance(user.getSchool().getEnable_attendance());
		 school.setEnableFees(user.getSchool().getEnable_fees());
		 school.setEnablePrintedWorksheet(user.getSchool().getEnable_attendance());
		 userInfo.setSchool(school);
		 p.setUserInfo(userInfo);
		 return p;
	 }

}

