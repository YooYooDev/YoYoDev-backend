package com.yooyoo.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yooyoo.model.School;
import com.yooyoo.service.SchoolService;
import com.yooyoo.util.FileUtils;
import com.yooyoo.vo.ResultVO;
import com.yooyoo.vo.SchoolInfo;

@RestController
@RequestMapping(value = { "/schools" })
public class SchoolController {

	Logger logger = LoggerFactory.getLogger(SchoolController.class);

	@Autowired
	public SchoolService schoolService;
	
	@PostMapping("/save")
	public ResponseEntity<Boolean> saveSchool(@RequestBody School school) {
		logger.info("Save School Method hit "+school.getEmailId());
		
		try {
			schoolService.saveSchool(school);
		} catch (Exception e) {
			logger.debug("Error While Save Save the School Details");
			logger.error("Error While Save Save the School Details"+e.getStackTrace());
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@PostMapping("/edit")
	public ResponseEntity<Boolean> editSchool(@RequestBody School school) {
		logger.info("Edit School Method hit ");
		try {
			schoolService.saveSchool(school);
		} catch (Exception e) {
			logger.debug("Error While Edit   School Details");
			logger.error("Error While Edit   School Details"+e.getStackTrace());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public  ResponseEntity<Boolean>  deleteSchool(@PathVariable("id") int id) {
		logger.info("Delete School Method hit "+id);
		try {
				schoolService.deleteSchool(id);
		}
		catch (Exception e) {
			logger.debug("Error While Delete School Details");
			logger.error("Error While Delete School Details"+e.getStackTrace());
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	@GetMapping("/load")
	public ResponseEntity<List<School>> loadSchool(){
		logger.info("load School Method hit ");
		List<School> schoolList = new ArrayList<School>();
		try {
			schoolList=schoolService.loadSchool();
		}
		catch (Exception e) {
			logger.debug("Error While load School Details");
			logger.error("Error While load School Details"+e.getStackTrace());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(schoolList, HttpStatus.OK);
	}
	@GetMapping("/getSchool/{id}")
	public  ResponseEntity<School>  getSchoolById(@PathVariable("id") int id) {
		logger.info("Delete School Method hit "+id);
		School school= null;
		try {
				school = schoolService.getSchoolById(id);
		}
		catch (Exception e) {
			logger.debug("Error While getting School Details");
			logger.error("Error While getting School Details"+e.getStackTrace());
			return new ResponseEntity<>(school, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(school, HttpStatus.OK);
	}
	
	@PostMapping("/uploadSchools")
	public ResponseEntity<ResultVO> uploadStudents(@RequestParam("file") final MultipartFile csvFile) {
		logger.info("School Upload Method hit ");
		ResultVO result =  new ResultVO();
		try {
			String file = csvFile.getOriginalFilename();
			if(".csv".equalsIgnoreCase(FileUtils.checkFileExtension(file))){
			List<SchoolInfo> schools = FileUtils.readAllDataAtOnceForSchool(csvFile);
			schoolService.uploadSchoolCsv(schools);
			result.setMessge("students uplocaded sucessFully");
			result.setStatus(200);
			}else{
				result.setMessge("Invalid file format. Please upload .csv files");
				result.setStatus(400);
			}
		} catch (Exception e) {
			result.setMessge("students upload operation failed..");
			result.setStatus(500);
			logger.debug("Error While getting  the students Details");
			logger.error("Error While getting  the students Details" + e.getStackTrace());
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	
}
