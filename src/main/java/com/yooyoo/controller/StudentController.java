package com.yooyoo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yooyoo.service.StudentService;
import com.yooyoo.vo.ResultVO;
import com.yooyoo.vo.StudentVO;

@RestController
@RequestMapping(value={"/students"})
public class StudentController {
	
	@Autowired
	StudentService studentService;
	Logger logger = LoggerFactory.getLogger(StudentController.class);

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResultVO createStudent(@RequestBody StudentVO student) {
        studentService.saveStudent(student);
        logger.info("UserDetailas :- "+student);
        ResultVO vo = new ResultVO();
	     vo.setMessge("student created sucessfully"); 
	     vo.setStatus(200);
	     return vo;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResultVO updateStudent(@RequestBody StudentVO student) {
		studentService.saveStudent(student);
		logger.info("UserDetailas :- " + student);
		ResultVO vo = new ResultVO();
		vo.setMessge("student updated sucessfully");
		vo.setStatus(200);
		return vo;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ResultVO deleteStudent(@RequestBody String id) {
		Integer studentId = Integer.parseInt(id);
		studentService.deleteStudent(studentId);
		ResultVO vo = new ResultVO();
		vo.setMessge("student has been deleted sucessfully");
		vo.setStatus(200);
		return vo;
		
	}
	
	@RequestMapping(value = "/getAllStudents", method = RequestMethod.GET)
	public List<StudentVO> getAllStudentBySchool(@RequestBody String id) {
		Integer schoolId = Integer.parseInt(id);
		List<StudentVO> students = studentService.getAllStudentsBySchool(schoolId);
		logger.info("UserDetailas :- " + id);
		logger.info("Student infos are fetched...");
		return students;
	}
	

	@GetMapping("/getStudentsBySchoolAndClass")
	public ResponseEntity<List<StudentVO>> getStudentsBySchoolANdClass(@RequestBody StudentVO student) {
		logger.info("Student with school and classId Method hit ");
		List<StudentVO> students = null;
		try {
			students = studentService.getStudentsBySchoolAndClass(student);
		} catch (Exception e) {
			logger.debug("Error While getting  the students Details");
			logger.error("Error While getting  the students Details"+e.getStackTrace());
			return new ResponseEntity<>(students, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(students, HttpStatus.OK);
	}

}
