package com.yooyoo.controller;

import java.util.List;

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

import com.yooyoo.service.QuizService;
import com.yooyoo.vo.QuizVO;
import com.yooyoo.vo.ResultVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = { "/quiz" })
@Slf4j
public class QuizController {
	
	@Autowired
	private QuizService service;
	
	@PostMapping("/save")
	public ResponseEntity<ResultVO> saveQuiz(@RequestBody QuizVO quiz) {
		log.info("Quiz Method hit "+quiz.getQuizName());
		ResultVO result = null;
		try {
			result = service.saveQuiz(quiz);
		} catch (Exception e) {
			log.debug("Error While Save  the Quiz Details"+e);
			log.error("Error While Save  the Quiz Details"+e.getStackTrace());
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResultVO> deleteQuiz(@PathVariable("id") Integer id) {
		log.info("Delete Quiz Method hit "+id);
		ResultVO result = null;
		try {
			result = service.deleteQuiz(id);
		} catch (Exception e) {
			log.debug("Error While Save  the Quiz Details"+e);
			log.error("Error While Save  the Quiz Details"+e.getStackTrace());
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/getAllQuizs")
	public ResponseEntity<List<QuizVO>> getAllQuiz() {
		log.info("Quiz Method hit ");
		List<QuizVO> quizs = null;
		try {
			quizs = service.getAllQuiz();
		} catch (Exception e) {
			log.debug("Error While Save  the Quiz Details"+e);
			log.error("Error While Save  the Quiz Details"+e.getStackTrace());
			return new ResponseEntity<>(quizs, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(quizs, HttpStatus.OK);
	}

}
