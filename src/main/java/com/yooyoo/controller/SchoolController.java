package com.yooyoo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yooyoo.model.School;
import com.yooyoo.service.SchoolService;
import com.yooyoo.vo.SchoolInfo;

@RestController
@RequestMapping(value = { "/schools" })
public class SchoolController {

	Logger logger = LoggerFactory.getLogger(SchoolController.class);

	@Autowired
	public SchoolService schoolService;
	
	@PostMapping("/save")
	public ResponseEntity<Boolean> saveSchool(@RequestBody School school) {
		logger.info("Save School Method hit "+school.getEmailId());
		
		try {
			schoolService.saveSchool(school);
		} catch (Exception e) {
			logger.debug("Error While Save Save the School Details");
			logger.error("Error While Save Save the School Details"+e.getStackTrace());
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@GetMapping("/edit/{id}")
	public ResponseEntity<Boolean> editSchool(@PathVariable("id") long id) {
		logger.info("Edit School Method hit "+id);
		
		try {
			//schoolService.editSchool(school);
		} catch (Exception e) {
			logger.debug("Error While Edit   School Details");
			logger.error("Error While Edit   School Details"+e.getStackTrace());
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	
}
