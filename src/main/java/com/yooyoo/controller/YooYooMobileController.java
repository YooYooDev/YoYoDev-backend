package com.yooyoo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = { "/mobile" })
public class YooYooMobileController {
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testMobile(){
		System.out.println("I am form mobile API...");
		return "Hello Mobile";
	}
	
	

}
