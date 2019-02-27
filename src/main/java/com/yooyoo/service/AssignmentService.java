package com.yooyoo.service;

import java.util.List;


import com.yooyoo.model.Assignment;
import com.yooyoo.model.Topic;
import com.yooyoo.vo.AssignmentVO;
import com.yooyoo.vo.ResultVO;

public interface AssignmentService {
	
	ResultVO createAssignment(AssignmentVO assignment);
	
	ResultVO updateAssignment(AssignmentVO assignment);
	
	ResultVO deleteAssignMent(Integer id);
	
	List<Assignment> getAllAssignment();
	
	List<Assignment> getAssignmentBySchool(Integer id);

	List<Topic> getTopicsBySubject(Integer id);
	
	

}
