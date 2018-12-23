package com.yooyoo.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public ResponseEntity<School> editSchool(@PathVariable("id") long id) {
		logger.info("Edit School Method hit "+id);
		School schoolDetails= new School();
		try {
			schoolDetails=schoolService.editSchool(id);
		} catch (Exception e) {
			logger.debug("Error While Edit   School Details");
			logger.error("Error While Edit   School Details"+e.getStackTrace());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(schoolDetails, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public  ResponseEntity<Boolean>  deleteSchool(@PathVariable("id") long id) {
		logger.info("Delete School Method hit "+id);
		try {
				schoolService.deleteSchool(id);
		}
		catch (Exception e) {
			logger.debug("Error While Delete School Details");
			logger.error("Error While Delete School Details"+e.getStackTrace());
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	@GetMapping("/load")
	public ResponseEntity<Set<School>> loadSchool(){
		logger.info("load School Method hit ");
		Set<School> schoolList = new HashSet<School>();
		try {
			schoolList=schoolService.loadSchool();
		}
		catch (Exception e) {
			logger.debug("Error While load School Details");
			logger.error("Error While load School Details"+e.getStackTrace());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(schoolList, HttpStatus.OK);
	}

	
}
