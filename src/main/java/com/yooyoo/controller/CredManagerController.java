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
import org.springframework.web.bind.annotation.RestController;

import com.yooyoo.service.CredService;
import com.yooyoo.vo.CredManagerVO;
import com.yooyoo.vo.ResultVO;

@RestController
@RequestMapping(value = { "/cred" })
public class CredManagerController {
	
	Logger logger = LoggerFactory.getLogger(CredManagerController.class);

	@Autowired
	public CredService service;
	
	@PostMapping("/save")
	public ResponseEntity<Boolean> saveCredManager(@RequestBody CredManagerVO credVO) {
		logger.info("Save CredUser Method hit "+credVO.getEmail());
		
		try {
			service.save(credVO);
		} catch (Exception e) {
			logger.debug("Error While Save Save the School Details");
			logger.error("Error While Save Save the School Details"+e.getStackTrace());
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<Boolean> updateCredManager(@RequestBody CredManagerVO credVO) {
		logger.info("update CredUser Method hit "+credVO.getEmail());
		
		try {
			service.update(credVO);
		} catch (Exception e) {
			logger.debug("Error While update Save the School Details");
			logger.error("Error While update Save the School Details"+e.getStackTrace());
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	@PostMapping("/resetpassword")
	public ResponseEntity<Boolean> sendPasswordEmail(@RequestBody CredManagerVO credVO) {
		logger.info("update CredUser Method hit "+credVO.getEmail());
		
		try {
			service.sendPasswordEmail(credVO);
		} catch (Exception e) {
			logger.debug("Error While update Save the School Details");
			logger.error("Error While update Save the School Details"+e.getStackTrace());
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	@PostMapping("/delete/{id}")
	public ResponseEntity<ResultVO> deleteCredManager(@PathVariable("id") int id) {
		ResultVO vo = null;
		try {
			vo = service.deleteCredManager(id);
		} catch (Exception e) {
			logger.debug("Error While delete Save the Cred Details");
			logger.error("Error While delete Save the Cred Details"+e.getStackTrace());
			return new ResponseEntity<>(vo, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}
	
	@GetMapping("/load/{id}")
	public ResponseEntity<CredManagerVO> getCredManager(@PathVariable("id") int id) {
		CredManagerVO vo = null;
		try {
			 vo = service.getCredManager(id);
		} catch (Exception e) {
			logger.debug("Error While getting Save the Cred Details");
			logger.error("Error While getting Save the Cred Details"+e.getStackTrace());
			return new ResponseEntity<>(vo, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(vo, HttpStatus.OK);
	}
	
	@GetMapping("/loadBySchool/{schoolId}")
	public ResponseEntity<List<CredManagerVO>> getCredManagerBySchool(@PathVariable("schoolId") int id) {
		List<CredManagerVO> vos = null;
		try {
			 vos = service.getCredManagerBySchool(id);
		} catch (Exception e) {
			logger.debug("Error While getting Save the Cred Details");
			logger.error("Error While getting Save the Cred Details"+e.getStackTrace());
			return new ResponseEntity<>(vos, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(vos, HttpStatus.OK);
	}
	
	@GetMapping("/loadAll")
	public ResponseEntity<List<CredManagerVO>> getCredManagers() {
		List<CredManagerVO> vos = null;
		try {
			 vos = service.getAllCredManager();
		} catch (Exception e) {
			logger.debug("Error While getting Save the Cred Details");
			logger.error("Error While getting Save the Cred Details"+e.getStackTrace());
			return new ResponseEntity<>(vos, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(vos, HttpStatus.OK);
	}
	
	

}
