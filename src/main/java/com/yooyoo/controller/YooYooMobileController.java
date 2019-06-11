package com.yooyoo.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yooyoo.model.Result;
import com.yooyoo.service.AssignmentService;
import com.yooyoo.service.AttendanceService;
import com.yooyoo.service.NotificationService;
import com.yooyoo.service.QuizService;
import com.yooyoo.service.ReportService;
import com.yooyoo.service.SessionService;
import com.yooyoo.service.StudentService;
import com.yooyoo.vo.CurriculamVO;
import com.yooyoo.vo.FeedBackVO;
import com.yooyoo.vo.MobileAssignmentVO;
import com.yooyoo.vo.MobileAttendanceVO;
import com.yooyoo.vo.NotificationsVO;
import com.yooyoo.vo.QuestionVO;
import com.yooyoo.vo.ReportVO;
import com.yooyoo.vo.ResultVO;
import com.yooyoo.vo.StudentVO;
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
	
	@Autowired
	QuizService quizService;
	
	@Autowired
	ReportService reportService;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	StudentService studentService;
	
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
			@PathVariable("grade") String grade, HttpServletRequest request) {
		logger.info("getAll Assignment  Method hit ");
		List<QuestionVO> questions = null;
		try {
			final String val = request.getHeader("accessToken");
			questions = service.getAssignmentQuestionsBySchoolAndGrade(schoolId, grade, val);
		} catch (Exception e) {
			logger.debug("Error While Save the getting assignments by school and grade" + e.getMessage());
			logger.error("Error While Save the getting assignments by school and grade" + e.getStackTrace());
			return new ResponseEntity<>(questions, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}
	
	@GetMapping("/getCurriculamsBySchool/{schoolId}/{month}")
	public ResponseEntity<List<CurriculamVO>> getCurriculamBySchool(@PathVariable("schoolId") int schoolId,
			@PathVariable("month") String month) {
		logger.info("getAll curriculam  Method hit ");
		List<CurriculamVO> curriculams = null;
		try {
			if(month.length() == 3){
			curriculams = service.getCurriculamsForSchool(schoolId, month);
			}
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
			e.printStackTrace();
			logger.debug("Error While Save the getting assignments by school and grade" + e.getMessage());
			logger.error("Error While Save the getting assignments by school and grade" + e.getStackTrace());
			return new ResponseEntity<>(vo, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}

	@GetMapping("/getAssignmentsById/{id}")
	public ResponseEntity<List<MobileAssignmentVO>> getAssignmentsByAssignmentId(@PathVariable("id") int assignmentId) {
		logger.info("getAll Assignment  Method hit ");
		List<MobileAssignmentVO> assignments = null;
		try {
			assignments = service.getAssignmentByAssignmentId(assignmentId);
		} catch (Exception e) {
			logger.debug("Error While Save the getting assignments by school and grade" + e.getMessage());
			logger.error("Error While Save the getting assignments by school and grade" + e.getStackTrace());
			return new ResponseEntity<>(assignments, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(assignments, HttpStatus.OK);
	}
	
	@GetMapping("/getVideosByAssignmentId/{id}")
	public ResponseEntity<List<VideoVO>> getAssignmentsVideosByAssignment(@PathVariable("id") int assignmentId) {
		logger.info("getAll Assignment  Method hit ");
		List<VideoVO> videos = null;
		try {
			videos = service.getVideosByAssignmentId(assignmentId);
		} catch (Exception e) {
			logger.debug("Error While Save the getting assignments by school and grade" + e.getMessage());
			logger.error("Error While Save the getting assignments by school and grade" + e.getStackTrace());
			return new ResponseEntity<>(videos, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(videos, HttpStatus.OK);
	}
	
	@GetMapping("/getQuestionsByAssignmentId/{id}")
	public ResponseEntity<List<QuestionVO>> getAssignmentsQuestionsByAssignment(@PathVariable("id") int assignmentId,
			HttpServletRequest request) {
		logger.info("getAll Assignment  Method hit ");
		List<QuestionVO> questions = null;
		try {
			final String val = request.getHeader("accessToken");
			System.out.println("It is YooYooMobile Conntroller...Token is :-" + val);
			questions = service.getQuestionsByAssignmentId(assignmentId, val);
		} catch (Exception e) {
			logger.debug("Error While Save the getting assignments by school and grade" + e.getMessage());
			logger.error("Error While Save the getting assignments by school and grade" + e.getStackTrace());
			return new ResponseEntity<>(questions, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}
	
	
	@GetMapping("/getAttendanceForStudent/{id}")
	public ResponseEntity<MobileAttendanceVO> getAttendanceByMonth(@PathVariable("id") int studentId,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
		logger.info("getattendance  Method hit ");
		MobileAttendanceVO attendances = null;
		try {
			DateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
			try {
				Date date = formatter.parse(fromDate);
				Date date1 = formatter.parse(toDate);
				attendances = attendanceService.getAttendancesByUseridAndMonth(studentId, date, date1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
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
	
	
	@PostMapping("/results/saveresult")
	public ResponseEntity<ResultVO> saveResult(@RequestBody List<Result> results) {
		logger.info("update mobile  notification by notification id  Method hit ");
		ResultVO vo = null;
		try {
			vo = quizService.saveResult(results);
		} catch (Exception e) {
			logger.debug("Error getting notification" + e.getMessage());
			logger.error("Error getting notification" + e.getStackTrace());
			return new ResponseEntity<>(vo, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}
	
	@GetMapping("/getReport/{id}")
	public ResponseEntity<ReportVO> getReport(@PathVariable("id") int studentId) {
		logger.info("getReport  Method hit ");
		ReportVO vo = null;
		try {
			vo = reportService.getReportVO(studentId);
		} catch (Exception e) {
			logger.debug("Error getting report" + e.getMessage());
			logger.error("Error getting report" + e.getStackTrace());
			return new ResponseEntity<>(vo, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}
	
	@PostMapping("/profile/update")
	public ResponseEntity<ResultVO> updateUser(@RequestBody StudentVO student) {
		logger.info("update Student iprovide from mobile ");
		ResultVO vo = null;
		try {
			vo = studentService.updateStudent(student);
		} catch (Exception e) {
			logger.debug("Error updating student profile" + e.getMessage());
			logger.error("Error updating student profile" + e.getStackTrace());
			return new ResponseEntity<>(vo, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}

}
