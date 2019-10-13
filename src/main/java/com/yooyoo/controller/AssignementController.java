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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yooyoo.model.Assignment;
import com.yooyoo.model.Topic;
import com.yooyoo.service.AssignmentService;
import com.yooyoo.vo.AssignmentVO;
import com.yooyoo.vo.ResultVO;


@RestController
@RequestMapping(value = { "/assignment" })
public class AssignementController {
	
	Logger logger = LoggerFactory.getLogger(AssignementController.class);
	
	@Autowired
	AssignmentService service;
	
	@PostMapping("/save")
	public ResponseEntity<ResultVO> saveAttendance(@RequestBody AssignmentVO assignment) {
		logger.info("Save Assignment  Method hit ");
		ResultVO result = null;
		try {
			result = service.createAssignment(assignment);
		} catch (Exception e) {
			logger.debug("Error While Save the saveAttendance Details"+e.getMessage());
			logger.error("Error While Save the saveAttendance Details"+e.getStackTrace());
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	@GetMapping("/getAllAssignments")
	public ResponseEntity<List<Assignment>> getAllAssignments() {
		logger.info("getAll Assignment  Method hit ");
		List<Assignment> assignments = null;
		try {
			assignments = service.getAllAssignment();
		} catch (Exception e) {
			logger.debug("Error While Save the saveAttendance Details"+e.getMessage());
			logger.error("Error While Save the saveAttendance Details"+e.getStackTrace());
			return new ResponseEntity<>(assignments, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(assignments, HttpStatus.OK);
	}
	
	@GetMapping("/getAssignmentsBySchool/{id}")
	public ResponseEntity<List<Assignment>> getAssignmentsBySchool(@PathVariable("id") int id) {
		logger.info("getAll Assignment  Method hit ");
		List<Assignment> assignments = null;
		try {
			assignments = service.getAssignmentBySchool(id);
		} catch (Exception e) {
			logger.debug("Error While Save the getting assignments by school"+e.getMessage());
			logger.error("Error While Save the getting assignments by school"+e.getStackTrace());
			return new ResponseEntity<>(assignments, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(assignments, HttpStatus.OK);
	}
	
	
	@GetMapping("/getTopicsBySubject/{id}")
	public ResponseEntity<List<Topic>> getTopicBySubject(@PathVariable("id") Integer id) {
		logger.info("get topics by  assignmennts  Method hit ");
		List<Topic> topics = null;
		try {
			topics = service.getTopicsBySubject(id);
		} catch (Exception e) {
			logger.debug("Error While get topics by  assignmennts Details"+e.getMessage());
			logger.error("Error While get topics by  assignmennts Details"+e.getStackTrace());
			return new ResponseEntity<>(topics, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(topics, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResultVO deleteAssignment(@PathVariable("id") int id) {

		ResultVO vo = service.deleteAssignMent(id);

		return vo;
	}

}
