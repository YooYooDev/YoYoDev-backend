package com.yooyoo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yooyoo.model.School;
import com.yooyoo.service.AttendanceService;
import com.yooyoo.vo.AttendanceDTO;
import com.yooyoo.vo.AttendanceVO;

@RestController
@RequestMapping(value = { "/attendance" })
public class AttendanceController {
	
	Logger logger = LoggerFactory.getLogger(AttendanceController.class);
	
	@Autowired
	private AttendanceService attendanceService; 
	
	@PostMapping("/save")
	public ResponseEntity<Boolean> saveAttendance(@RequestBody AttendanceDTO attendance) {
		logger.info("Save saveAttendance Method hit "+attendance.getSchoolId());
		try {
			attendanceService.save(attendance);
		} catch (Exception e) {
			logger.debug("Error While Save the saveAttendance Details"+e.getMessage());
			logger.error("Error While Save the saveAttendance Details"+e.getStackTrace());
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
}
