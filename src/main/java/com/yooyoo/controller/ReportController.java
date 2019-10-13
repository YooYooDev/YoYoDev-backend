package com.yooyoo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yooyoo.service.ReportService;
import com.yooyoo.vo.ReportVO;
import com.yooyoo.vo.SchoolReportVO;
import com.yooyoo.vo.StudentReportVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = { "/report" })
@Slf4j
public class ReportController {
	
	@Autowired
	ReportService service;
	
	public static DateFormat FORMATTER = new SimpleDateFormat("dd-MMM-yy");
	
	@GetMapping("/getReportBySchool/{id}")
	public ResponseEntity<ReportVO> getReportsBySchool(@PathVariable("id") Integer schoolId) {
		log.info("getAll Assignment  Method hit ");
		ReportVO reportVO = null;
		try {
			reportVO = service.getSchoolReport(schoolId);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Error While Save the saveAttendance Details"+e.getMessage());
			log.error("Error While Save the saveAttendance Details"+e.getStackTrace());
			
			return new ResponseEntity<>(reportVO, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(reportVO, HttpStatus.OK);
	}
	
	@GetMapping("/getassignmentreportbyschool/{id}")
	public ResponseEntity<SchoolReportVO> getAssignmentReportsBySchool(@PathVariable("id") Integer schoolId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
		log.info("getAll Assignment Report  Method hit ");
		SchoolReportVO reportVO = null;
		try {
			Date date = FORMATTER.parse(fromDate);
			Date date1 = FORMATTER.parse(toDate);
			reportVO = service.getAssignmentReportForSchool(schoolId, date, date1);
		} catch (Exception e) {
			log.debug("Error While Save the saveAttendance Details"+e.getMessage());
			log.error("Error While Save the saveAttendance Details"+e.getStackTrace());
			
			return new ResponseEntity<>(reportVO, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(reportVO, HttpStatus.OK);
	}
	
	@GetMapping("/getattnreportbyschool/{id}")
	public ResponseEntity<SchoolReportVO> getAttnReportsBySchool(@PathVariable("id") Integer schoolId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
		log.info("getAll Attendance  Method hit ");
		SchoolReportVO reportVO = null;
		try {
			Date date = FORMATTER.parse(fromDate);
			Date date1 = FORMATTER.parse(toDate);
			reportVO = service.getAttendanceReportForSchool(schoolId, date, date1);
		} catch (Exception e) {
			log.debug("Error While Save the saveAttendance Details"+e.getMessage());
			log.error("Error While Save the saveAttendance Details"+e.getStackTrace());
			
			return new ResponseEntity<>(reportVO, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(reportVO, HttpStatus.OK);
	}
	
	@GetMapping("/getfeereportbyschool/{id}")
	public ResponseEntity<SchoolReportVO> getFeeReportsBySchool(@PathVariable("id") Integer schoolId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
		log.info("getAll Assignment  Method hit ");
		SchoolReportVO reportVO = null;
		try {
			Date date = FORMATTER.parse(fromDate);
			Date date1 = FORMATTER.parse(toDate);
			reportVO = service.getFeeReportForSchool(schoolId, date, date1);
		} catch (Exception e) {
			log.debug("Error While Save the saveAttendance Details"+e.getMessage());
			log.error("Error While Save the saveAttendance Details"+e.getStackTrace());
			
			return new ResponseEntity<>(reportVO, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(reportVO, HttpStatus.OK);
	}

	
	@GetMapping("/getReportByStudent/{id}")
	public ResponseEntity<StudentReportVO> getReportsByStudent(@PathVariable("id") Integer studentId) {
		log.info("getAll Assignment  Method hit ");
		StudentReportVO reportVO = null;
		try {
			reportVO = service.getStudentReport(studentId);
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Error While getting student report Details" + e.getMessage());
			log.error("Error While getting student report Details" + e.getStackTrace());
			return new ResponseEntity<>(reportVO, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(reportVO, HttpStatus.OK);
	}


}
