package com.yooyoo.serviceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.yooyoo.model.Assignment;
import com.yooyoo.model.Grade;
import com.yooyoo.model.School;
import com.yooyoo.model.Subject;
import com.yooyoo.model.Topic;
import com.yooyoo.repository.AssignmentRepository;
import com.yooyoo.repository.GradeRepository;
import com.yooyoo.service.AssignmentService;
import com.yooyoo.service.CurriculumService;
import com.yooyoo.service.SchoolService;
import com.yooyoo.vo.AssignmentVO;
import com.yooyoo.vo.ResultVO;

@Service
public class AssignmentServiceImpl implements AssignmentService{
	
	@Autowired
	AssignmentRepository assignmentRepository;
	
	@Autowired
	CurriculumService curriculamService;
	
	@Autowired
	SchoolService schoolService;
	
	@Autowired
	GradeRepository gradeRepo;
	
	@Override
	public ResultVO createAssignment(AssignmentVO assignmentVO) {
		ResultVO result = new ResultVO();
		DateFormat formatter;
		Date date = null;
		if (assignmentVO != null) {
			try {
				Topic topic = curriculamService.getTopicById(assignmentVO.getTopicId());
				Subject subject = curriculamService.getSubjectById(assignmentVO.getSubjectId());
				School school = schoolService.getSchoolById(assignmentVO.getSchoolId());
				if (assignmentVO.getGrade().equalsIgnoreCase("all")) {
					Iterable<Grade> itr = gradeRepo.findAll();
					for (Grade g : itr) {
						Assignment assignment = new Assignment();
						assignment.setGrade(g);
						assignment.setTopic(topic);
						assignment.setSubject(subject);
						assignment.setSchool(school);
						assignmentRepository.save(assignment);
						formatter = new SimpleDateFormat("dd-MMM-yy");
						date = formatter.parse(assignmentVO.getDate());
						assignment.setDate(date);
					}
				} else {
					Grade grade = gradeRepo.getGradebyName(assignmentVO.getGrade());
					Assignment assignment = new Assignment();
					assignment.setGrade(grade);
					assignment.setTopic(topic);
					assignment.setSubject(subject);
					assignment.setSchool(school);
					formatter = new SimpleDateFormat("dd-MMM-yy");
					date = formatter.parse(assignmentVO.getDate());
					assignment.setDate(date);
					assignmentRepository.save(assignment);
				}

				result.setStatus(200);
				result.setMessage("Assignment Saved Sucessfully...");
			} catch (DataIntegrityViolationException e) {
				result.setStatus(400);
				result.setMessage("Duplicate Record.....");
			}catch(Exception e) {
				result.setStatus(400);
				result.setMessage("Unable to save Assignment...");
			}
		}
		return result;
	}

	@Override
	public ResultVO updateAssignment(AssignmentVO assignmentVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultVO deleteAssignMent(Integer id) {
		ResultVO result = new ResultVO();
		if(id!=0){
		Optional<Assignment> assignment = assignmentRepository.findById(id);
		assignmentRepository.delete(assignment.get());
		result.setStatus(200);
		result.setMessage("Assignment deleted sucessfully...");
		}else{
			result.setStatus(400);
			result.setMessage("Unable to delete Assignment...");
		}
		return result;
	}

	@Override
	public List<Assignment> getAllAssignment() {
		List<Assignment> assignments = new ArrayList<>();
		Iterable<Assignment> assigns= assignmentRepository.findAll();
		for(Assignment a : assigns){
			assignments.add(a);
		}
		return assignments;
	}
	
	

	@Override
	public List<Assignment> getAssignmentBySchool(Integer id) {
		List<Assignment> assignments = new ArrayList<>();
		Iterable<Assignment> assigns= assignmentRepository.getAssignmentsBySchool(id);
		for(Assignment a : assigns){
			assignments.add(a);
		}
		return assignments;
	}

	@Override
	public List<Topic> getTopicsBySubject(Integer id) {
		return curriculamService.getTopicBySubjectId(id);
	}

}
