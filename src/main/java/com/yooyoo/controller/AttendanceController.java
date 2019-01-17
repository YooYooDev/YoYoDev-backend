package com.yooyoo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yooyoo.service.AttendanceService;
import com.yooyoo.vo.AttendanceVO;

@RestController
@RequestMapping(value = { "/attendance" })
public class AttendanceController {
	
	Logger logger = LoggerFactory.getLogger(AttendanceController.class);
	
	@Autowired
	private AttendanceService attendanceService; 
	
	@PostMapping("/save")

	public ResponseEntity<Boolean> saveAttendance(@RequestBody AttendanceVO attendance) {
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
	
	@GetMapping("/load/{schoolId}")
	public ResponseEntity<List<AttendanceVO>> getAttendance(@PathVariable("schoolId") int schoolId) {
		logger.info("get Attendance Method hit " + schoolId);
		List<AttendanceVO> atts = null;
		try {
			atts = attendanceService.getAttandanceDetails(schoolId);
		} catch (Exception e) {
			logger.debug("Error While Save the saveAttendance Details" + e.getMessage());
			logger.error("Error While Save the saveAttendance Details" + e.getStackTrace());
			return new ResponseEntity<>(atts, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(atts, HttpStatus.OK);
	}
}
