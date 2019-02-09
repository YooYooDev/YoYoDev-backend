package com.yooyoo.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yooyoo.model.Category;
import com.yooyoo.model.School;
import com.yooyoo.model.Subject;
import com.yooyoo.service.CurriculumService;
import com.yooyoo.vo.ResultVO;

@RestController
@RequestMapping(value = { "/curriculum" })
public class CurriculumController {
	
	Logger logger = LoggerFactory.getLogger(CurriculumController.class);
	
	@Autowired
	private CurriculumService curriculumService;
	
	@PostMapping("/create-subject")
	public ResponseEntity<String> createSubject(@RequestBody Subject subject) {
		logger.info("create-subject API Method hit " + subject.getName());
		subject.setCreatedDate(new Date());
		subject.setUpdatedDate(new Date());
		try {
			curriculumService.saveSubject(subject);
		} catch (Exception e) {
			logger.debug("Error While  Save the Subject Details");
			logger.error("Error While  Save the Subject Details" + e.getStackTrace());
			return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	@PostMapping("/create-category")
	public ResponseEntity<String> createCategory(@RequestBody Category category) {
		logger.info("create-category API Method hit " + category.getName());
		category.setCreatedDate(new Date());
		category.setUpdatedDate(new Date());
		try {
			curriculumService.saveCategory(category);
		} catch (Exception e) {
			logger.debug("Error While Save the Category Details");
			logger.error("Error While Save the Category Details" + e.getStackTrace());
			return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
}
