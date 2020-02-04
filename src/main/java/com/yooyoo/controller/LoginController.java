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
import com.yooyoo.vo.LoginVO;
import com.yooyoo.vo.Profile;
import com.yooyoo.vo.ResultVO;
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
		 p.setRole(user.getRole().getName());
		 UserInfo userInfo = new UserInfo();
		 userInfo.setEmail(user.getEmail());
		 userInfo.setUserName(user.getUser_name());
		 userInfo.setFullName(user.getFullName());
		 userInfo.setMobile(user.getMobile());
		 userInfo.setLocation(user.getCity());
		 SchoolInfo school = new SchoolInfo();
		 school.setName(user.getSchool().getName());
		 school.setEmailId(user.getSchool().getEmailId());
		 school.setOwnerName(user.getSchool().getOwnerName());
		 school.setEnableAttendance(user.getSchool().getEnableAttendance());
		 school.setEnableFees(user.getSchool().getEnableFees());
		 school.setEnablePrintedWorksheet(user.getSchool().getEnablePrintedWorksheet());
		 school.setActive(user.getSchool().getActive());
		 school.setAddress(user.getSchool().getAddress());
		 school.setCode(user.getSchool().getCode());
		 school.setRegistrationName(user.getSchool().getRegistrationName());
		 school.setState(user.getSchool().getState());
		 school.setId(user.getSchool().getId());
		 userInfo.setSchool(school);
		 p.setUserInfo(userInfo);
		 return p;
	 }
	 
	 @RequestMapping(value = "/yooyoo/mobilelogin", method = RequestMethod.POST)
	 public Profile loginMobileUser(@RequestBody LoginVO userForm) {
		 logger.info("Loggedin User details  :- "+userForm.getEmail()+":"+userForm.getPassword()+":"+userForm.getSchoolCode());
		 Profile profile = loginService.authenticateMobileUser(userForm);
	        logger.info("UserDetails for mobile  :- "+profile);
		    return profile;  
		}
	 
	 @RequestMapping(value = "/yooyoo/b2c/mobilelogin", method = RequestMethod.POST)
	 public Profile loginB2CMobileUser(@RequestBody LoginVO userForm) {
		 logger.info("Loggedin User details  :- "+userForm.getMobileNo()+":"+userForm.getOtp());
		 Profile profile = loginService.authenticateB2CMobileUser(userForm);
	        logger.info("UserDetails for mobile  :- "+profile);
		    return profile;  
		}
	 
	 @RequestMapping(value = "/yooyoo/b2c/sendotp", method = RequestMethod.POST)
	 public boolean sendOtp(@RequestBody LoginVO userForm) {
		 logger.info("Otp Send API called");
		 boolean otpSent = loginService.sendOtpForB2C(userForm);
	        logger.info("Otp Send API called");
		    return otpSent;  
		}
	 
	 @RequestMapping(value = "/yooyoo/forgotpassword", method = RequestMethod.POST)
	 public ResultVO sendEmail(@RequestBody LoginVO userForm) {
		 ResultVO result = loginService.sendForgotPasswordEmail(userForm);
	        logger.info("UserDetails for mobile  :- "+result.getMessage());
		    return result;  
		}


}

