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
import org.springframework.web.bind.annotation.RestController;

import com.yooyoo.service.TicketsServise;
import com.yooyoo.vo.TicketVO;

@RestController
@RequestMapping(value = { "/tickets" })
public class TicketsController {
	
	Logger logger = LoggerFactory.getLogger(TicketsController.class);
	
	@Autowired
	TicketsServise servise;
	
	@PostMapping("/save")
	public ResponseEntity<Boolean> saveSchool(@RequestBody TicketVO ticket) {
		logger.info("Save Ticket Method hit ");
		
		try {
			servise.saveTicket(ticket);
		} catch (Exception e) {
			logger.debug("Error While storing the ticket");
			logger.error("Error While storing the ticket"+e.getStackTrace());
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@PostMapping("/edit")
	public ResponseEntity<Boolean> editSchool(@RequestBody TicketVO ticket) {
		logger.info("Edit School Method hit ");
		try {
			servise.saveTicket(ticket);
		} catch (Exception e) {
			logger.debug("Error While Edit   School Details");
			logger.error("Error While Edit   School Details"+e.getStackTrace());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public  ResponseEntity<Boolean>  deleteSchool(@PathVariable("id") int id) {
		logger.info("Delete ticket Method hit "+id);
		try {
				servise.deleteTickets(id);
		}
		catch (Exception e) {
			logger.debug("Error While Delete ticket Details");
			logger.error("Error While Delete ticket Details"+e.getStackTrace());
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	@GetMapping("/load")
	public ResponseEntity<List<TicketVO>> loadSchool(){
		logger.info("load ticket Method hit ");
		List<TicketVO> tickets = new ArrayList<TicketVO>();
		try {
			tickets=servise.getAllTickets();
		}
		catch (Exception e) {
			logger.debug("Error While load ticket Details");
			logger.error("Error While load ticket Details"+e.getStackTrace());
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(tickets, HttpStatus.OK);
	}
	@GetMapping("/getTickets/{ticketId}")
	public  ResponseEntity<List<TicketVO>>  getSchoolById(@PathVariable("ticketId") int id) {
		logger.info("Delete ticket Method hit "+id);
		List<TicketVO> ticket= new ArrayList<TicketVO>();
		try {
			ticket = servise.getAllTicketBySchool();
		}
		catch (Exception e) {
			logger.debug("Error While getting ticket Details");
			logger.error("Error While getting ticket Details"+e.getStackTrace());
			return new ResponseEntity<>(ticket, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(ticket, HttpStatus.OK);
	}

}
