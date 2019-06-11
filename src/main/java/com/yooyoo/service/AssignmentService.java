package com.yooyoo.service;

import java.util.List;


import com.yooyoo.model.Assignment;
import com.yooyoo.model.Topic;
import com.yooyoo.vo.AssignmentVO;
import com.yooyoo.vo.CurriculamVO;
import com.yooyoo.vo.MobileAssignmentVO;
import com.yooyoo.vo.QuestionVO;
import com.yooyoo.vo.QuizVO;
import com.yooyoo.vo.ResultVO;
import com.yooyoo.vo.VideoVO;

public interface AssignmentService {
	
	ResultVO createAssignment(AssignmentVO assignment);
	
	ResultVO updateAssignment(AssignmentVO assignment);
	
	ResultVO deleteAssignMent(Integer id);
	
	List<Assignment> getAllAssignment();
	
	List<Assignment> getAssignmentBySchool(Integer id);

	List<Topic> getTopicsBySubject(Integer id);
	
	//services  for mobile application
	
	List<MobileAssignmentVO> getAssignmentBySchoolAndGrade(Integer schoolId, String grade);
	
	List<CurriculamVO> getCurriculamsForSchool(int schoolId, String month); 
	
	MobileAssignmentVO finaAssignmentById(int assignMentid);

	List<VideoVO> getAssignmentVideosBySchoolAndGrade(Integer schoolId, String grade);

	List<QuestionVO> getAssignmentQuestionsBySchoolAndGrade(Integer schoolId, String grade, String token);

	List<QuizVO> getQuizByTopic(Integer topicId);

	List<MobileAssignmentVO> getAssignmentByAssignmentId(Integer assignmentId);
	
	List<VideoVO> getVideosByAssignmentId(Integer assignmentId);
	
	List<QuestionVO> getQuestionsByAssignmentId(Integer assignmentId, String val);
	
	

}
