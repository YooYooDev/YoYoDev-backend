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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yooyoo.service.StudentService;
import com.yooyoo.util.FileUtils;
import com.yooyoo.vo.GradeVO;
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
		ResultVO vo = null;
		vo  = studentService.saveStudent(student);
        logger.info("UserDetailas :- "+student);
	     return vo;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResultVO updateStudent(@RequestBody StudentVO student) {
		logger.info("UserDetailas :- " + student);
		ResultVO vo =null;
		vo = studentService.updateStudent(student);
		return vo;
	}
	
	@DeleteMapping("/delete/{id}")
	public ResultVO deleteStudent(@PathVariable("id") int id) {
		studentService.deleteStudent(id);
		ResultVO vo = new ResultVO();
		String message = "student has been deleted sucessfully";
		vo.getMessge().add(message);
		vo.setStatus(200);
		return vo;
		
	}
	
	@GetMapping("/getAllStudents/{schoolId}")
	public List<StudentVO> getAllStudentBySchool(@PathVariable("schoolId") int schoolId) {
		List<StudentVO> students = studentService.getAllStudentsBySchool(schoolId);
		logger.info("UserDetailas :- " + schoolId);
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
	
	@GetMapping("/getStudentsBySchool/{id}")
	public ResponseEntity<List<GradeVO>> getStudentsBySchoolANdClass(@PathVariable("id")int id) {
		logger.info("Student with class Method hit ");
		List<GradeVO> grades = null;
		try {
			grades = studentService.getStudentsBySchoolAndGrade(id);
		} catch (Exception e) {
			logger.debug("Error While getting  the students Details");
			logger.error("Error While getting  the students Details"+e.getStackTrace());
			return new ResponseEntity<>(grades, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(grades, HttpStatus.OK);
	}
	

	@PostMapping("/uploadStudents/{schoolId}")
	public ResponseEntity<ResultVO> uploadStudents(@PathVariable("schoolId") final int id,
			@RequestParam("file") final MultipartFile csvFile) {
		logger.info("Student with class Method hit ");
		ResultVO result =  new ResultVO();
		try {
			String file = csvFile.getOriginalFilename();
			if(".csv".equalsIgnoreCase(FileUtils.checkFileExtension(file))){
			List<StudentVO> students = FileUtils.readAllDataAtOnce(csvFile,id);
			studentService.uploadUserCsv(students);
			result.setMessage("students uplocaded sucessFully");
			result.setStatus(200);
			}else{
				result.setMessage("Invalid file format. Please upload .csv files");
				result.setStatus(400);
			}
		} catch (Exception e) {
			result.setMessage("students upload operation failed..");
			result.setStatus(500);
			logger.debug("Error While getting  the students Details");
			logger.error("Error While getting  the students Details" + e.getStackTrace());
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
