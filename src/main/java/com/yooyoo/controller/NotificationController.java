package com.yooyoo.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yooyoo.service.NotificationService;
import com.yooyoo.vo.NotificationsVO;
import com.yooyoo.vo.ResultVO;

@RestController
@RequestMapping(value = { "/notifications" })
public class NotificationController {
	
	@Autowired
	public NotificationService service;
	
	Logger logger = LoggerFactory.getLogger(NotificationController.class);
	
	@PostMapping("/save")
	public ResponseEntity<Boolean> saveNotification(@RequestBody NotificationsVO notification) {
		logger.info("notification header Method hit "+notification.getHeader());
		
		try {
			service.saveNotification(notification);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Error While Save Save the notification Details");
			logger.error("Error While Save Save the notification Details"+e.getStackTrace());
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Boolean> updateNotification(@RequestBody NotificationsVO notification) {
		logger.info("notification header Method hit "+notification.getHeader());
		
		try {
			service.saveNotification(notification);
		} catch (Exception e) {
			logger.debug("Error While Save Save the notification Details");
			logger.error("Error While Save Save the notification Details"+e.getStackTrace());
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	@GetMapping("/getAllNotifications")
	public ResponseEntity<List<NotificationsVO>> getAllNotifications() {
		logger.info("notification header Method hit ");
		List<NotificationsVO> notis = null;
		try {
			 notis = service.getAllNotifications();
		} catch (Exception e) {
			logger.debug("Error While getting  the notification Details");
			logger.error("Error While getting  the notification Details"+e.getStackTrace());
			return new ResponseEntity<>(notis, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(notis, HttpStatus.OK);
	}
	
	@GetMapping("/getNotificationsBySchool/{id}")
	public ResponseEntity<List<NotificationsVO>> getAllNotificationsBySchoolANdClass(@PathVariable("id") Integer id) {
		logger.info("notification header Method hit ");
		List<NotificationsVO> notis = null;
		try {
			 notis = service.getNotificationsBySchoolANdClass(id);
		} catch (Exception e) {
			logger.debug("Error While getting  the notification Details");
			e.printStackTrace();
			return new ResponseEntity<>(notis, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(notis, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResultVO deleteStudent(@PathVariable("id") int id, @RequestParam("delete") boolean delete) {
		service.deleteNotificaitons(id,delete);
		ResultVO vo = new ResultVO();
		String message = "Notification has been deleted sucessfully";
		vo.setMessage(message);
		vo.setStatus(200);
		return vo;
	}
	
	

}
