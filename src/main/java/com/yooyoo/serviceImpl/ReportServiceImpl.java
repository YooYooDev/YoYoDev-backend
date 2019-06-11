package com.yooyoo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yooyoo.model.Attendance;
import com.yooyoo.model.Result;
import com.yooyoo.repository.AttendanceRepository;
import com.yooyoo.repository.ResultRepository;
import com.yooyoo.service.FeesService;
import com.yooyoo.service.ReportService;
import com.yooyoo.vo.FeesVO;
import com.yooyoo.vo.ReportVO;
import com.yooyoo.vo.StudentFeeVO;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	ResultRepository resultRepository;

	@Autowired
	AttendanceRepository attendanceRepository;

	@Autowired
	FeesService feeService;

	@Override
	public ReportVO getReportVO(Integer studentId) {
		ReportVO report = new ReportVO();
		List<Result> results = resultRepository.getResultByStudentId(studentId);
		List<Attendance> attn = attendanceRepository.getAttendansesByStudent(studentId);
		StudentFeeVO stVo = new StudentFeeVO();
		stVo.setId(studentId);
		FeesVO fees = feeService.getFeesByStudent(stVo);
		if (results != null && !results.isEmpty()) {
			List<Result> l = results.stream().filter(r -> r.getResult()).collect(Collectors.toList());
			report.setNoOfQuestionFaced(results.size());
			report.setNoOfCorrectAnswers(l.size());
		}

		if (attn != null && !attn.isEmpty()) {
			report.setAttendanceTakenDays(attn.size());
			report.setPresentDays(attn.stream().filter(r -> r.getStatus() == 1).collect(Collectors.toList()).size());
		}
		if (fees != null) {
			report.setTotalFees(fees.getTotalBillAmount());
			report.setOutstandingFees(fees.getPendingAmount());
		}

		return report;
	}

	@Override
	public ReportVO getSchoolReport(Integer schoolId) {
		ReportVO report = new ReportVO();
		List<Attendance> attn = attendanceRepository.getAttendansesBySchool(schoolId);
		List<Result> results = resultRepository.getResultByStudentId(schoolId);
		List<FeesVO> fees = feeService.getAllFeesBySchool(schoolId);

		if (results != null && !results.isEmpty()) {
			List<Result> l = results.stream().filter(r -> r.getResult()).collect(Collectors.toList());
			report.setNoOfQuestionFaced(results.size());
			report.setNoOfCorrectAnswers(l.size());
		}

		if (attn != null && !attn.isEmpty()) {
			report.setAttendanceTakenDays(attn.size());
			report.setPresentDays(attn.stream().filter(r -> r.getStatus() == 1).collect(Collectors.toList()).size());
		}
		if (fees != null && !fees.isEmpty()) {
			Float fullAmount = 0.0f;
			Float pendingAmount = 0.0f;
			fullAmount = fees.stream().map(e -> e.getTotalBillAmount()).reduce(0F, Float::sum);
			pendingAmount = fees.stream().map(e -> e.getPendingAmount()).reduce(0F, Float::sum);
			report.setTotalFees(fullAmount);
			report.setOutstandingFees(pendingAmount);

		}
		return report;
	}

}
