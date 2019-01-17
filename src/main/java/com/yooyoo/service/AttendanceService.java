package com.yooyoo.service;

import java.util.List;

import com.yooyoo.vo.AttendanceVO;

public interface AttendanceService {
	boolean save(AttendanceVO attendance);

	List<AttendanceVO> getAttandanceDetails(int schoolId);

}
