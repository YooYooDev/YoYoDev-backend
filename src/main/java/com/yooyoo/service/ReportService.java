package com.yooyoo.service;

import com.yooyoo.vo.ReportVO;

public interface ReportService {
	
	ReportVO getReportVO(Integer studentId);
	
	ReportVO getSchoolReport(Integer schoolId);

}
