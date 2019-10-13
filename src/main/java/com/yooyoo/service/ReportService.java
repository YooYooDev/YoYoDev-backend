package com.yooyoo.service;

import java.util.Date;

import com.yooyoo.vo.MobileReportVO;
import com.yooyoo.vo.ReportVO;
import com.yooyoo.vo.SchoolReportVO;
import com.yooyoo.vo.StudentReportVO;

public interface ReportService {
	
	ReportVO getReportVO(Integer studentId);
	
	StudentReportVO getStudentReport(Integer studentId);
	
	ReportVO getSchoolReport(Integer schoolId);
	
	SchoolReportVO getAttendanceReportForSchool(Integer studentId, Date toDate, Date fromDate);
	
	SchoolReportVO getFeeReportForSchool(Integer studentId, Date toDate, Date fromDate);
	
	SchoolReportVO getAssignmentReportForSchool(Integer studentId, Date toDate, Date fromDate);

	MobileReportVO getMobileReportVO(int studentId, String fromDate, String toDate);
	
	
	
	
	

}
