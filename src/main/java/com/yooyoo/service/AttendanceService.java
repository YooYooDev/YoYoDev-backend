package com.yooyoo.service;

import java.util.Date;
import java.util.List;

import com.yooyoo.vo.AttendanceVO;
import com.yooyoo.vo.MobileAttendanceVO;

public interface AttendanceService {
	boolean save(AttendanceVO attendance);

	List<AttendanceVO> getAttandanceDetails(int schoolId);
	
	
	MobileAttendanceVO getAttendancesByUseridAndMonth(int userId, Date date, Date date1);
	

}
