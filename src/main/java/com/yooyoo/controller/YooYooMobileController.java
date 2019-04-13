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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yooyoo.service.AssignmentService;
import com.yooyoo.service.AttendanceService;
import com.yooyoo.service.NotificationService;
import com.yooyoo.vo.CurriculamVO;
import com.yooyoo.vo.FeedBackVO;
import com.yooyoo.vo.MobileAssignmentVO;
import com.yooyoo.vo.MobileAttendanceVO;
import com.yooyoo.vo.NotificationsVO;
import com.yooyoo.vo.QuestionVO;
import com.yooyoo.vo.ResultVO;
import com.yooyoo.vo.VideoVO;

@RestController
@RequestMapping(value = { "/mobile" })
public class YooYooMobileController {
	
Logger logger = LoggerFactory.getLogger(YooYooMobileController.class);
	
	@Autowired
	AssignmentService service;
	
	@Autowired
	AttendanceService attendanceService;
	
	@Autowired
	NotificationService notificationService;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testMobile(){
		System.out.println("I am form mobile API...");
		return "Hello Mobile";
	}
	
	@GetMapping("/getAssignmentsBySchoolAndGrade/{id}/{grade:.+}")
	public ResponseEntity<List<MobileAssignmentVO>> getAssignmentsBySchool(@PathVariable("id") int schoolId,
			@PathVariable("grade") String grade) {
		logger.info("getAll Assignment  Method hit ");
		List<MobileAssignmentVO> assignments = null;
		try {
			assignments = service.getAssignmentBySchoolAndGrade(schoolId, grade);
		} catch (Exception e) {
			logger.debug("Error While Save the getting assignments by school and grade" + e.getMessage());
			logger.error("Error While Save the getting assignments by school and grade" + e.getStackTrace());
			return new ResponseEntity<>(assignments, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(assignments, HttpStatus.OK);
	}
	
	@GetMapping("/getAssignmentsVideosBySchoolAndGrade/{id}/{grade:.+}")
	public ResponseEntity<List<VideoVO>> getAssignmentsVideosBySchool(@PathVariable("id") int schoolId,
			@PathVariable("grade") String grade) {
		logger.info("getAll Assignment  Method hit ");
		List<VideoVO> videos = null;
		try {
			videos = service.getAssignmentVideosBySchoolAndGrade(schoolId, grade);
		} catch (Exception e) {
			logger.debug("Error While Save the getting assignments by school and grade" + e.getMessage());
			logger.error("Error While Save the getting assignments by school and grade" + e.getStackTrace());
			return new ResponseEntity<>(videos, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(videos, HttpStatus.OK);
	}
	
	@GetMapping("/getAssignmentsQuestionsBySchoolAndGrade/{id}/{grade:.+}")
	public ResponseEntity<List<QuestionVO>> getAssignmentsQuestionsBySchool(@PathVariable("id") int schoolId,
			@PathVariable("grade") String grade) {
		logger.info("getAll Assignment  Method hit ");
		List<QuestionVO> questions = null;
		try {
			questions = service.getAssignmentQuestionsBySchoolAndGrade(schoolId, grade);
		} catch (Exception e) {
			logger.debug("Error While Save the getting assignments by school and grade" + e.getMessage());
			logger.error("Error While Save the getting assignments by school and grade" + e.getStackTrace());
			return new ResponseEntity<>(questions, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}
	
	@GetMapping("/getCurriculamsBySchool/{schoolId}")
	public ResponseEntity<List<CurriculamVO>> getCurriculamBySchool(@PathVariable("schoolId") int schoolId) {
		logger.info("getAll curriculam  Method hit ");
		List<CurriculamVO> curriculams = null;
		try {
			curriculams = service.getCurriculamsForSchool(schoolId);
		} catch (Exception e) {
			logger.debug("Error While Save the getting assignments by school and grade" + e.getMessage());
			logger.error("Error While Save the getting assignments by school and grade" + e.getStackTrace());
			return new ResponseEntity<>(curriculams, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(curriculams, HttpStatus.OK);
	}
	
	@GetMapping("/getAssignmentById/{id}")
	public ResponseEntity<MobileAssignmentVO> getAssignmentbyId(@PathVariable("id") int assignMentId) {
		logger.info("getAll curriculam  Method hit ");
		MobileAssignmentVO vo = null;
		try {
			vo = service.finaAssignmentById(assignMentId);
		} catch (Exception e) {
			logger.debug("Error While Save the getting assignments by school and grade" + e.getMessage());
			logger.error("Error While Save the getting assignments by school and grade" + e.getStackTrace());
			return new ResponseEntity<>(vo, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}
	
	
	@GetMapping("/getAttendanceForStudent/{id}/{month}")
	public ResponseEntity<List<MobileAttendanceVO>> getAttendanceByMonth(@PathVariable("id") int studentId,
			@PathVariable("month") String month) {
		logger.info("getattendance  Method hit ");
		List<MobileAttendanceVO> attendances = null;
		try {
			attendances = attendanceService.getAttendancesByUseridAndMonth(studentId, month);
		} catch (Exception e) {
			logger.debug("Error While Save the getting attendance by student and month" + e.getMessage());
			logger.error("Error While Save the getting attendance by student and month" + e.getStackTrace());
			return new ResponseEntity<>(attendances, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(attendances, HttpStatus.OK);
	}
	
	@GetMapping("/getNotifications/{userId}")
	public ResponseEntity<List<NotificationsVO>> getNotificationByUserId(@PathVariable("userId") int userId) {
		logger.info("get mobile  notification by user id  Method hit ");
		List<NotificationsVO> vo = null;
		try {
			vo = notificationService.getNotificationByUser(userId);
		} catch (Exception e) {
			logger.debug("Error getting notification" + e.getMessage());
			logger.error("Error getting notification" + e.getStackTrace());
			return new ResponseEntity<>(vo, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}
	
	@GetMapping("/updateNotification/{notificationId}")
	public ResponseEntity<ResultVO> updateNotificatoin(@PathVariable("notificationId") int notificationId) {
		logger.info("update mobile  notification by notification id  Method hit ");
		ResultVO vo = null;
		try {
			vo = notificationService.updateNotification(notificationId);
		} catch (Exception e) {
			logger.debug("Error getting notification" + e.getMessage());
			logger.error("Error getting notification" + e.getStackTrace());
			return new ResponseEntity<>(vo, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}
	
	
	@PostMapping("/feedback/save")
	public ResponseEntity<ResultVO> saveFeedback(@RequestBody FeedBackVO feedBack) {
		logger.info("update mobile  notification by notification id  Method hit ");
		ResultVO vo = null;
		try {
			vo = notificationService.saveFeedBack(feedBack);
		} catch (Exception e) {
			logger.debug("Error getting notification" + e.getMessage());
			logger.error("Error getting notification" + e.getStackTrace());
			return new ResponseEntity<>(vo, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}
	
	
	
	
	

}
