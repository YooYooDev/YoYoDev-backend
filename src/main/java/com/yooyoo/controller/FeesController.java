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

import com.yooyoo.service.FeesService;
import com.yooyoo.vo.FeesVO;

@RestController
@RequestMapping(value = { "/fees" })
public class FeesController {
	
	Logger logger = LoggerFactory.getLogger(FeesController.class);

	@Autowired
	public FeesService feeService;
	
	@PostMapping("/save")
	public ResponseEntity<Boolean> saveFees(@RequestBody FeesVO fees) {
		logger.info("Save Fees Method hit "+fees.getStudentName());
		
		try {
			feeService.saveFeesForStudent(fees);
		} catch (Exception e) {
			logger.debug("Error While Save Save the Fees Details");
			logger.error("Error While Save Save the Fees Details"+e.getStackTrace());
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<Boolean> updateFees(@RequestBody FeesVO fees) {
		logger.info("Save Fees Method hit " + fees.getStudentName());

		try {
			if (fees.getId() != null) {
				feeService.saveFeesForStudent(fees);
			} else {
				throw new NullPointerException();
			}

		} catch (Exception e) {
			logger.debug("Error While Save Save the Fees Details");
			logger.error("Error While Save Save the Fees Details" + e.getStackTrace());
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete/{feeId}")
	public ResponseEntity<Boolean> daleteFees(@PathVariable("feeId") int  feeId) {
		logger.info("Delete Fees Method hit " + feeId);

		try {
			if (feeId != 0) {
				feeService.deleteFees(feeId);
			} else {
				throw new NullPointerException();
			}

		} catch (Exception e) {
			logger.debug("Error While delete the Fees Details");
			logger.error("Error While delete the Fees Details" + e.getStackTrace());
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	@GetMapping("/getFeesbySchol/{schoolId}")
	public ResponseEntity<List<FeesVO>> getFeesBySchool(@PathVariable("schoolId") int  schoolId) {
		logger.info("get Fees by school Method hit " + schoolId);
        List<FeesVO> fees = null;
		try {
			if (schoolId != 0) {
				fees = feeService.getAllFeesBySchool(schoolId);
			} else {
				throw new NullPointerException();
			}

		} catch (Exception e) {
			logger.debug("Error While delete the Fees Details");
			logger.error("Error While delete the Fees Details" + e.getStackTrace());
			return new ResponseEntity<>(fees, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(fees, HttpStatus.OK);
	}


}
