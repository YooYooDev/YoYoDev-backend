package com.yooyoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yooyoo.model.B2CCustomer;
import com.yooyoo.service.B2CService;
import com.yooyoo.vo.ResultVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = { "/b2c" })
@Slf4j
public class B2CController {
	
	@Autowired
	B2CService b2cService;
	
	@PostMapping("/register")
	public ResponseEntity<ResultVO> saveAttendance(@RequestBody B2CCustomer b2cUser) {
		log.info("Inside register B2C customer");
		ResultVO vo = new ResultVO();
		try {
			vo = b2cService.register(b2cUser);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("B2C customer registration has been failed.");
			return new ResponseEntity<>(vo, HttpStatus.BAD_REQUEST);
		}
		log.info("B2C customer has been registered sucessfully...");
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResultVO> deleteB2cUser(@RequestBody B2CCustomer b2cUser) {
		log.info("Inside delete B2C customer");
		ResultVO vo = new ResultVO();
		try {
			vo = b2cService.deleteB2Cuser(b2cUser);
		} catch (Exception e) {
			log.info("B2C customer deleted has been failed.");
			return new ResponseEntity<>(vo, HttpStatus.BAD_REQUEST);
		}
		log.info("B2C customer has been deleted sucessfully...");
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}

}
