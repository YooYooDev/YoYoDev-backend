package com.yooyoo.service;

import java.util.List;

import com.yooyoo.vo.AttendanceVO;
import com.yooyoo.vo.MobileAttendanceVO;

public interface AttendanceService {
	boolean save(AttendanceVO attendance);

	List<AttendanceVO> getAttandanceDetails(int schoolId);
	
	
	List<MobileAttendanceVO> getAttendancesByUseridAndMonth(int userId, String month);
	

}
