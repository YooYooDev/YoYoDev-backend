package com.yooyoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yooyoo.service.ReportService;
import com.yooyoo.vo.ReportVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = { "/report" })
@Slf4j
public class ReportController {
	
	@Autowired
	ReportService service;
	
	@GetMapping("/getReportBySchool/{id}")
	public ResponseEntity<ReportVO> getReportsBySchool(@PathVariable("id") Integer schoolId) {
		log.info("getAll Assignment  Method hit ");
		ReportVO reportVO = null;
		try {
			reportVO = service.getSchoolReport(schoolId);
		} catch (Exception e) {
			log.debug("Error While Save the saveAttendance Details"+e.getMessage());
			log.error("Error While Save the saveAttendance Details"+e.getStackTrace());
			
			return new ResponseEntity<>(reportVO, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(reportVO, HttpStatus.OK);
	}

	
	@GetMapping("/getReportByStudent/{id}")
	public ResponseEntity<ReportVO> getReportsByStudent(@PathVariable("id") Integer studentId) {
		log.info("getAll Assignment  Method hit ");
		ReportVO reportVO = null;
		try {
			reportVO = service.getReportVO(studentId);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Error While getting student report Details" + e.getMessage());
			log.error("Error While getting student report Details" + e.getStackTrace());
			return new ResponseEntity<>(reportVO, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(reportVO, HttpStatus.OK);
	}


}
