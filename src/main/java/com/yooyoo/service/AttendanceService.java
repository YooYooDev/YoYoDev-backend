package com.yooyoo.service;

import java.util.Date;
import java.util.List;

import com.yooyoo.vo.AttendanceVO;
import com.yooyoo.vo.MobileAttendanceVO;

public interface AttendanceService {
	boolean save(AttendanceVO attendance);

	List<AttendanceVO> getAttandanceDetails(int schoolId);
	
	AttendanceVO getAttandanceDetailsBySchoolAndGrade(int schoolId, String grade);
	
	
	MobileAttendanceVO getAttendancesByUseridAndMonth(int userId, Date date, Date date1);
	

}
