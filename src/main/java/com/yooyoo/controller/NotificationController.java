package com.yooyoo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yooyoo.service.NotificationService;
import com.yooyoo.vo.NotificationsVO;

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
	
	@GetMapping("/getNotificationsBySchool")
	public ResponseEntity<List<NotificationsVO>> getAllNotificationsBySchoolANdClass(@RequestBody NotificationsVO notification) {
		logger.info("notification header Method hit ");
		List<NotificationsVO> notis = null;
		try {
			 notis = service.getNotificationsBySchoolANdClass(notification);
		} catch (Exception e) {
			logger.debug("Error While getting  the notification Details");
			e.printStackTrace();
			return new ResponseEntity<>(notis, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(notis, HttpStatus.OK);
	}
	
	

}
