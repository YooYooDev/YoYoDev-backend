package com.yooyoo.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yooyoo.model.Category;
import com.yooyoo.model.Subject;
import com.yooyoo.model.Topic;
import com.yooyoo.service.CurriculumService;
import com.yooyoo.vo.ResultVO;

@RestController
@RequestMapping(value = { "/curriculum" })
public class CurriculumController {

	Logger logger = LoggerFactory.getLogger(CurriculumController.class);

	@Autowired
	private CurriculumService curriculumService;

	@PostMapping("/create-subject")
	public ResponseEntity<String> createSubject(@RequestBody Subject subject) {
		logger.info("create-subject API Method hit " + subject.getName());
		subject.setCreatedDate(new Date());
		subject.setUpdatedDate(new Date());
		try {
			curriculumService.saveSubject(subject);
		} catch (Exception e) {
			logger.debug("Error While  Save the Subject Details");
			logger.error("Error While  Save the Subject Details" + e.getStackTrace());
			return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("success", HttpStatus.OK);
	}

	@PostMapping("/create-category")
	public ResponseEntity<String> createCategory(@RequestBody Category category) {
		logger.info("create-category API Method hit " + category.getName());
		category.setCreatedDate(new Date());
		category.setUpdatedDate(new Date());
		try {
			curriculumService.saveCategory(category);
		} catch (Exception e) {
			logger.debug("Error While Save the Category Details");
			logger.error("Error While Save the Category Details" + e.getStackTrace());
			return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("success", HttpStatus.OK);
	}

	@PutMapping("/update-subject")
	public ResponseEntity<String> updateSubject(@RequestBody Subject subject) {
		logger.info("update-subject API Method hit " + subject.getName());
		subject.setUpdatedDate(new Date());
		try {
			if (subject.getId() != null) {
				curriculumService.saveSubject(subject);
			} else {
				return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			logger.debug("Error While  Save the Subject Details");
			logger.error("Error While  Save the Subject Details" + e.getStackTrace());
			return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("success", HttpStatus.OK);
	}

	@PutMapping("/update-category")
	public ResponseEntity<String> updateCategory(@RequestBody Category category) {
		logger.info("update-category API Method hit " + category.getName());
		category.setUpdatedDate(new Date());
		try {
			if (category.getId() != null) {
				curriculumService.saveCategory(category);
			} else {
				return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			logger.debug("Error While Save the Category Details");
			logger.error("Error While Save the Category Details" + e.getStackTrace());
			return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("success", HttpStatus.OK);
	}

	@GetMapping("/getSubjects")
	public ResponseEntity<List<Subject>> getSubjects() {
		logger.info("get Subject  Method hit ");
		List<Subject> subs = null;
		try {
			subs = curriculumService.getAllSubjects();
		} catch (Exception e) {
			logger.debug("Error While getting  the Subject Details");
			logger.error("Error While getting  the Subject Details" + e.getStackTrace());
			return new ResponseEntity<>(subs, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(subs, HttpStatus.OK);
	}

	@GetMapping("/getCategories")
	public ResponseEntity<List<Category>> getCategories() {
		logger.info("get category Method hit ");
		List<Category> cats = null;
		try {
			cats = curriculumService.getAllCategories();
		} catch (Exception e) {
			logger.debug("Error While getting  the Category Details");
			logger.error("Error While getting  the Category Details" + e.getStackTrace());
			return new ResponseEntity<>(cats, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(cats, HttpStatus.OK);
	}
	

	@DeleteMapping("/delete-subject")
	public ResponseEntity<String> deleteSubject(@RequestBody Subject subject) {
		logger.info("update-subject API Method hit " + subject.getName());
		subject.setUpdatedDate(new Date());
		try {
			if (subject.getId() != null) {
				curriculumService.deleteSubject(subject);
			} else {
				return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			logger.debug("Error While  Save the Subject Details");
			logger.error("Error While  Save the Subject Details" + e.getStackTrace());
			return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("success", HttpStatus.OK);
	}

	@DeleteMapping("/delete-category")
	public ResponseEntity<String> deleteCategory(@RequestBody Category category) {
		logger.info("update-category API Method hit " + category.getName());
		category.setUpdatedDate(new Date());
		try {
			if (category.getId() != null) {
				curriculumService.deleteCategory(category);
			} else {
				return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			logger.debug("Error While Save the Category Details");
			logger.error("Error While Save the Category Details :-" + e);
			return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	@PostMapping("/create-topic")
	public ResponseEntity<ResultVO> createTopic(@RequestBody Topic topic) {
		logger.info("create-topic API Method hit " + topic.getName());
		topic.setCreatedDate(new Date());
		topic.setUpdatedDate(new Date());
		ResultVO result = null;
		try {
			result = curriculumService.createTopic(topic);
		} catch (Exception e) {
			logger.debug("Error While  Save the topic Details");
			logger.error("Error While  Save the topic Details" + e.getStackTrace());
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PutMapping("/update-topic")
	public ResponseEntity<ResultVO> updateTopic(@RequestBody Topic topic) {
		logger.info("update-topic API Method hit " + topic.getName());
		topic.setCreatedDate(new Date());
		topic.setUpdatedDate(new Date());
		ResultVO result = null;
		if(topic.getId() == null){
			result = new ResultVO();
			result.setStatus(400);
			result.setMessage("Id not present for update topic");
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		try {
			result = curriculumService.updateTopic(topic);
		} catch (Exception e) {
			logger.debug("Error While  update the topic Details");
			logger.error("Error While  update the topic Details" + e.getStackTrace());
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/getAllTopics")
	public ResponseEntity<List<Topic>> getAllTopics() {
		logger.info("get All topic API Method hit ");
		List<Topic> result = null;
		
		try {
			result = curriculumService.getAllTopics();
		} catch (Exception e) {
			logger.debug("Error While  getting all  the topic Details");
			logger.error("Error While  getting all  the topic Details" + e.getStackTrace());
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResultVO> deleteTopic(@PathVariable("id") Integer id) {
		logger.info("delete topic API Method hit ");
		ResultVO result = null;
		
		try {
			result = curriculumService.deleteTopic(id);
		} catch (Exception e) {
			logger.debug("Error While  getting all  the topic Details");
			logger.error("Error While  getting all  the topic Details" + e.getStackTrace());
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	

	
}
