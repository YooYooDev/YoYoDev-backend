package com.yooyoo.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yooyoo.model.Fees;
import com.yooyoo.repository.FeeRepository;
import com.yooyoo.service.FeesService;

@RestController
@RequestMapping(value = { "/images" })
public class ImagesController {
	Logger logger = LoggerFactory.getLogger(ImagesController.class);

	@Autowired
	public FeesService feeService;
	
	@Autowired
	FeeRepository repository;
	
	@PostMapping("/upload/{feeId}")
	public ResponseEntity<Boolean> saveFees(@RequestParam("image") MultipartFile image,
			@PathVariable("feeId") Integer feeId) {
		logger.info("Save Fees Method hit ");

		try {

			feeService.saveImageFile(feeId, image);

		} catch (Exception e) {
			System.out.println(e);
			logger.debug("Error While Save Save the Fees Details");
			logger.error("Error While Save Save the Fees Details" + e.getStackTrace());
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getimage/{feeId}" ,  produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> getFees(@PathVariable("feeId") Integer feeId,HttpServletResponse response) {
		logger.info("Save Fees Method hit ");
		Fees fees = null;
		try {

			Optional<Fees> fee = repository.findById(feeId);
			fees = fee.get();

		} catch (Exception e) {
			System.out.println(e);
			logger.debug("Error While Save Save the Fees Details");
			logger.error("Error While Save Save the Fees Details" + e.getStackTrace());
			return new ResponseEntity<>(new byte[1], HttpStatus.BAD_REQUEST);
		}
		response.setContentType(MediaType.IMAGE_PNG_VALUE);
		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(fees.getImage1());
	}

}