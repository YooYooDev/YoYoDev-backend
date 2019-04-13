package com.yooyoo.serviceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.yooyoo.model.Assignment;
import com.yooyoo.model.Grade;
import com.yooyoo.model.QuestionMedia;
import com.yooyoo.model.School;
import com.yooyoo.model.Subject;
import com.yooyoo.model.Topic;
import com.yooyoo.repository.AssignmentRepository;
import com.yooyoo.repository.GradeRepository;
import com.yooyoo.repository.QuestionMediaRepository;
import com.yooyoo.service.AssignmentService;
import com.yooyoo.service.CurriculumService;
import com.yooyoo.service.QuizService;
import com.yooyoo.service.SchoolService;
import com.yooyoo.util.VOMapper;
import com.yooyoo.vo.AssignmentVO;
import com.yooyoo.vo.CurriculamVO;
import com.yooyoo.vo.MobileAssignmentVO;
import com.yooyoo.vo.QuestionVO;
import com.yooyoo.vo.QuizVO;
import com.yooyoo.vo.ResultVO;
import com.yooyoo.vo.VideoVO;

@Service
public class AssignmentServiceImpl implements AssignmentService{
	
	Logger logger = LoggerFactory.getLogger(AssignmentServiceImpl.class);
	@Autowired
	AssignmentRepository assignmentRepository;
	
	@Autowired
	CurriculumService curriculamService;
	
	@Autowired
	SchoolService schoolService;
	
	@Autowired
	GradeRepository gradeRepo;
	
	@Autowired
	QuestionMediaRepository questionMediaRepo;
	
	@Autowired
	QuizService quizService;
	
	@Value("${media.url}")
	private String mediaUrl; 
	
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

	@Override
	public List<MobileAssignmentVO> getAssignmentBySchoolAndGrade(Integer schoolId, String grade) {
		List<Assignment> assignments = null;
		List<MobileAssignmentVO> assignMentVos = new ArrayList<>();
		Grade gradeEntiry = gradeRepo.getGradebyName(grade);
		try {
			assignments = assignmentRepository.getAssignmentsBySchoolAndGrade(schoolId, gradeEntiry.getId());
			for (Assignment assignment : assignments) {
				MobileAssignmentVO assignmentVO = VOMapper.getAssignmentVO(assignment);
				assignMentVos.add(assignmentVO);
			}
		} catch (Exception e) {
			logger.error("internal seerver error" + e);
		}
		return assignMentVos;
	}

	@Override
	public List<CurriculamVO> getCurriculamsForSchool(int schoolId) {
		List<CurriculamVO> curries = new ArrayList<>();
		List<Assignment> assignments = getAssignmentBySchool(schoolId);
		if(assignments != null && !assignments.isEmpty()){
			for(Assignment ass: assignments){
				CurriculamVO cur = new CurriculamVO();
				cur.setAssignmentId(ass.getId());
				cur.setName(ass.getSubject().getName()+" : "+ass.getTopic().getName());
				DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");  
				String strDate = dateFormat.format(ass.getDate());
				cur.setDate(strDate);
				curries.add(cur);
			}
		}
		return curries;
	}

	@Override
	public MobileAssignmentVO finaAssignmentById(int assignMentid) {
		Optional<Assignment> assignment = assignmentRepository.findById(assignMentid);
		MobileAssignmentVO assignmentVO = VOMapper.getAssignmentVO(assignment.get());
		return assignmentVO;
	}
	
	@Override
	public List<VideoVO> getAssignmentVideosBySchoolAndGrade(Integer schoolId, String grade) {
		List<Assignment> assignments = null;
		List<VideoVO> videos = new ArrayList<>();
		Grade gradeEntiry = gradeRepo.getGradebyName(grade);
		try {
			assignments = assignmentRepository.getAssignmentsBySchoolAndGrade(schoolId, gradeEntiry.getId());
			for (Assignment assignment : assignments) {
				VideoVO vo = VOMapper.getVideoVO(assignment.getTopic());
				videos.add(vo);
			}
		} catch (Exception e) {
			logger.error("internal seerver error" + e);
		}
		return videos;
	}
	
	@Override
	public List<QuestionVO> getAssignmentQuestionsBySchoolAndGrade(Integer schoolId, String grade) {
		List<Assignment> assignments = null;
		List<QuestionVO> questions = new ArrayList<>();
		Grade gradeEntiry = gradeRepo.getGradebyName(grade);
		try {
			assignments = assignmentRepository.getAssignmentsBySchoolAndGrade(schoolId, gradeEntiry.getId());
			for (Assignment assignment : assignments) {
				List<QuizVO> quizs = quizService.getQuizByTopic(assignment.getTopic().getId());
				if (!quizs.isEmpty()) {
					List<QuestionVO> q = quizs.get(0).getQuestions();
					questions.addAll(q);
					for(QuestionVO qs: questions){
						QuestionMedia med = questionMediaRepo.findByQuestionId(qs.getId());
						if(med != null){
						qs.setContentType(med.getContentType());
						qs.setMediaLink(mediaUrl+qs.getId());
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("internal seerver error" + e);
		}
		return questions;
	}

}
