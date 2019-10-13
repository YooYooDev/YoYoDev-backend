package com.yooyoo.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yooyoo.model.Assignment;
import com.yooyoo.model.Attendance;
import com.yooyoo.model.Fees;
import com.yooyoo.model.Result;
import com.yooyoo.model.School;
import com.yooyoo.model.Student;
import com.yooyoo.model.Tracker;
import com.yooyoo.repository.AssignmentRepository;
import com.yooyoo.repository.AttendanceRepository;
import com.yooyoo.repository.FeeRepository;
import com.yooyoo.repository.ResultRepository;
import com.yooyoo.repository.StudentRepository;
import com.yooyoo.repository.TrackerRepository;
import com.yooyoo.service.AssignmentService;
import com.yooyoo.service.FeesService;
import com.yooyoo.service.ReportService;
import com.yooyoo.service.SchoolService;
import com.yooyoo.service.StudentService;
import com.yooyoo.vo.AssignmentReportVO;
import com.yooyoo.vo.AttendanceReportVO;
import com.yooyoo.vo.FeeReportVO;
import com.yooyoo.vo.FeesVO;
import com.yooyoo.vo.MobileReportVO;
import com.yooyoo.vo.ReportVO;
import com.yooyoo.vo.SchoolReportVO;
import com.yooyoo.vo.StudentFeeVO;
import com.yooyoo.vo.StudentReportVO;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	ResultRepository resultRepository;

	@Autowired
	AttendanceRepository attendanceRepository;

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	FeeRepository feeRepository;

	@Autowired
	FeesService feeService;
	
	@Autowired
	TrackerRepository trackerRepository;
	
	@Autowired
	AssignmentService assignmentService;
	
	@Autowired
	SchoolService schoolService;
	
	@Autowired
	AssignmentRepository assignmentRepo;

	@Override
	public ReportVO getReportVO(Integer studentId) {
		ReportVO report = new ReportVO();
		List<Result> results = resultRepository.getResultByStudentId(studentId);
		List<Attendance> attn = attendanceRepository.getAttendansesByStudent(studentId);
		List<Tracker> tracker = trackerRepository.getTrackingDataForStudent(studentId);
		StudentFeeVO stVo = new StudentFeeVO();
		stVo.setId(studentId);
		FeesVO fees = feeService.getFeesByStudent(stVo);
		if (results != null && !results.isEmpty()) {
			List<Result> l = results.stream().filter(r -> r.getResult()).collect(Collectors.toList());
			report.setNoOfQuestionFaced(results.size());
			report.setNoOfCorrectAnswers(l.size());
		} else {
			report.setNoOfQuestionFaced(0);
			report.setNoOfCorrectAnswers(0);
		}
		if (attn != null && !attn.isEmpty()) {
			report.setAttendanceTakenDays(attn.size());
			report.setPresentDays(attn.stream().filter(r -> r.getStatus() == 1).collect(Collectors.toList()).size());
		} else {
			report.setAttendanceTakenDays(0);
			report.setPresentDays(0);
		}
		if (fees != null) {
			report.setTotalTransportFee(fees.getTransportationFee().longValue());
			report.setTotalTutionFee(fees.getTutionFee().longValue());
			report.setTotalTransportFeePaid(fees.getPaidTransportFee() != null ? fees.getPaidTransportFee().longValue():0l);
			report.setTotalTutionFeepaid(fees.getPaidTutionFee() != null ?fees.getPaidTutionFee().longValue():0l);

		} else {
			report.setTotalTransportFee(0L);
			report.setTotalTutionFee(0L);
			report.setTotalTransportFeePaid(0L);
			report.setTotalTutionFeepaid(0L);
		}
		
		if (tracker != null) {
			Long noOfVid = tracker.stream().filter(t -> t.getTrackingType().equalsIgnoreCase(TrackerServiceImpl.VIDEO))
					.count();
			Long noOfWsheet = tracker.stream()
					.filter(t -> t.getTrackingType().equalsIgnoreCase(TrackerServiceImpl.WORKSHEET)).count();
			report.setNoOfVideosWatched(noOfVid.intValue());
			report.setNoOfWorksheetAppeared(noOfWsheet.intValue());

		} else {
			report.setNoOfVideosWatched(0);
			report.setNoOfWorksheetAppeared(0);

		}
		return report;
	}

	@Override
	public StudentReportVO getStudentReport(Integer studentId) {
		StudentReportVO report = new StudentReportVO();
		List<Result> results = resultRepository.getResultByStudentId(studentId);
		List<Attendance> attn = attendanceRepository.getAttendansesByStudent(studentId);
		Student student = studentRepository.findById(studentId).get();
		List<Tracker> tracker = trackerRepository.getTrackingDataForStudent(studentId);
		StudentFeeVO stVo = new StudentFeeVO();
		stVo.setId(studentId);
		report.setFirstName(student.getFirst_name());
		report.setSchoolName(student.getSchool().getName());
		report.setGradeName(student.getGrade().getName());
		FeesVO fees = feeService.getFeesByStudent(stVo);
		if (results != null && !results.isEmpty()) {
			List<Result> l = results.stream().filter(r -> r.getResult()).collect(Collectors.toList());
			report.setNoOfQuestionsFaced(results.size());
			report.setCorrectAnswers(l.size());
		}

		if (attn != null && !attn.isEmpty()) {
			report.setAttendanceTakenDays(attn.size());
			report.setPresentDays(attn.stream().filter(r -> r.getStatus() == 1).collect(Collectors.toList()).size());
		}
		if (fees != null) {
			report.setTransportationFee(fees.getTransportationFee());
			report.setTutionFee(fees.getTutionFee());
			report.setTotalTransportFeePaid(fees.getPaidTransportFee().intValue());
			report.setTotalTutionFeePaid(fees.getPaidTutionFee().intValue());
		}
		if (tracker != null) {
			Long noOfVid = tracker.stream().filter(t -> t.getTrackingType().equalsIgnoreCase(TrackerServiceImpl.VIDEO))
					.count();
			Long noOfWsheet = tracker.stream()
					.filter(t -> t.getTrackingType().equalsIgnoreCase(TrackerServiceImpl.WORKSHEET)).count();
			report.setNoOfVideoWatched(noOfVid != null? noOfVid.intValue() : 0);
			report.setNoOfWorkSheetAppeared(noOfWsheet != null ? noOfWsheet.intValue() : 0);

		} else {
			report.setNoOfVideoWatched(0);
			report.setNoOfWorkSheetAppeared(0);

		}
		return report;
	}

	@Override
	public ReportVO getSchoolReport(Integer schoolId) {
		ReportVO report = new ReportVO();
		List<Attendance> attn = attendanceRepository.getAttendansesBySchool(schoolId, new Date(), new Date());
		List<Result> results = resultRepository.getResultByStudentId(schoolId);
		List<FeesVO> fees = feeService.getAllFeesBySchool(schoolId);
		List<Assignment> assignments = assignmentService.getAssignmentBySchool(schoolId);
		School school = schoolService.getSchoolById(schoolId);

		if (school != null) {
			report.setSchoolName(school.getName());
			report.setSchoolCode(String.valueOf(school.getCode()));
			report.setOwnerName(school.getOwnerName());
		}

		if (assignments != null && !assignments.isEmpty()) {
			report.setNoOfTopicsAssigned(assignments.size());
		}
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
			Float totalTutionFee = 0.0f;
			Float pendingTutionFee = 0.0f;
			Float pendingTransportationFee = 0.0f;
			fullAmount = fees.stream().filter(e -> e.getTransportationFee()!=null).map(e -> e.getTransportationFee()).reduce(0F, Float::sum);
			totalTutionFee = fees.stream().filter(e ->  e.getTutionFee()!= null).map(e -> e.getTutionFee()).reduce(0F, Float::sum);
			pendingTutionFee = fees.stream().filter(e -> e.getPaidTutionFee() != null).map(e -> e.getPaidTutionFee()).reduce(0F, Float::sum);
			pendingTransportationFee = fees.stream().filter(e -> e.getPaidTransportFee() != null).map(e -> e.getPaidTransportFee()).reduce(0F, Float::sum);
			report.setTotalTransportFee(fullAmount.longValue());
			report.setTotalTutionFee(totalTutionFee.longValue());
			report.setTotalTutionFeepaid(pendingTutionFee.longValue());
			report.setTotalTransportFeePaid(pendingTransportationFee.longValue());
		}
		return report;
	}

	@Override
	public SchoolReportVO getAttendanceReportForSchool(Integer schoolId, Date fromDate, Date toDate) {
		SchoolReportVO schoolReportVO = new SchoolReportVO();
		List<Attendance> attn = attendanceRepository.getAttendansesBySchool(schoolId, fromDate, toDate);
		List<AttendanceReportVO> attendances = new ArrayList<>();
		AttendanceReportVO attndanceLkg = new AttendanceReportVO();
		AttendanceReportVO attndanceUkg = new AttendanceReportVO();
		AttendanceReportVO attndanceNurhsary = new AttendanceReportVO();
		attndanceNurhsary.setGradeName("NURSERY");
		attndanceNurhsary.setTotalAttendance(attn.stream()
				.filter(r -> (r.getGrade().getName().equalsIgnoreCase("NURSERY"))).collect(Collectors.toList()).size());
		attndanceNurhsary.setTotalPresentDays(
				attn.stream().filter(r -> (r.getStatus() == 1 && r.getGrade().getName().equalsIgnoreCase("NURSERY")))
						.collect(Collectors.toList()).size());

		attndanceLkg.setGradeName("L.K.G");
		attndanceLkg.setTotalAttendance(attn.stream().filter(r -> (r.getGrade().getName().equalsIgnoreCase("L.K.G")))
				.collect(Collectors.toList()).size());
		attndanceLkg.setTotalPresentDays(
				attn.stream().filter(r -> (r.getStatus() == 1 && r.getGrade().getName().equalsIgnoreCase("L.K.G")))
						.collect(Collectors.toList()).size());

		attndanceUkg.setGradeName("U.K.G");
		attndanceUkg.setTotalAttendance(attn.stream().filter(r -> (r.getGrade().getName().equalsIgnoreCase("U.K.G")))
				.collect(Collectors.toList()).size());
		attndanceUkg.setTotalPresentDays(
				attn.stream().filter(r -> (r.getStatus() == 1 && r.getGrade().getName().equalsIgnoreCase("U.K.G")))
						.collect(Collectors.toList()).size());
		attendances.add(attndanceNurhsary);
		attendances.add(attndanceLkg);
		attendances.add(attndanceUkg);
		schoolReportVO.setAttendaneReport(attendances);
		return schoolReportVO;

	}

	@Override
	public SchoolReportVO getFeeReportForSchool(Integer schoolId, Date fromDate, Date toDate) {
		SchoolReportVO schoolVO = new SchoolReportVO();
		List<FeeReportVO> feeReports = new ArrayList<>();
		FeeReportVO nursheryFees = new FeeReportVO();
		FeeReportVO ukgFees = new FeeReportVO();
		FeeReportVO lkgFees = new FeeReportVO();
		List<Fees> fees = feeRepository.findFeesBySchool(schoolId, fromDate, toDate);
		if (fees != null && !fees.isEmpty()) {

			Float totalTransportFeeN = fees.stream()
					.filter(f -> f.getStudent().getGrade().getName().equalsIgnoreCase("NURSHERY"))
					.map(e -> e.getTransportationFee()).reduce(0F, Float::sum);
			Float totalTutionFeeN = fees.stream()
					.filter(f -> f.getStudent().getGrade().getName().equalsIgnoreCase("NURSHERY"))
					.map(e -> e.getTutionFee()).reduce(0F, Float::sum);
			Float totalTransportFeePaidN = fees.stream()
					.filter(f -> f.getStudent().getGrade().getName().equalsIgnoreCase("NURSHERY"))
					.map(e -> e.getPaidTransportFee()).reduce(0F, Float::sum);
			Float totalTutionFeePaidN = fees.stream()
					.filter(f -> f.getStudent().getGrade().getName().equalsIgnoreCase("NURSHERY"))
					.map(e -> e.getPaidTutionFee()).reduce(0F, Float::sum);

			Float totalTransportFeeL = fees.stream()
					.filter(f -> f.getStudent().getGrade().getName().equalsIgnoreCase("L.K.G"))
					.map(e -> e.getTransportationFee()).reduce(0F, Float::sum);
			Float totalTutionFeeNL = fees.stream()
					.filter(f -> f.getStudent().getGrade().getName().equalsIgnoreCase("L.K.G"))
					.map(e -> e.getTutionFee()).reduce(0F, Float::sum);
			Float totalTransportFeePaidNL = fees.stream()
					.filter(f -> f.getStudent().getGrade().getName().equalsIgnoreCase("L.K.G"))
					.map(e -> e.getPaidTransportFee()).reduce(0F, Float::sum);
			Float totalTutionFeePaidNL = fees.stream()
					.filter(f -> f.getStudent().getGrade().getName().equalsIgnoreCase("L.K.G"))
					.map(e -> e.getPaidTutionFee()).reduce(0F, Float::sum);

			Float totalTransportFeeU = fees.stream()
					.filter(f -> f.getStudent().getGrade().getName().equalsIgnoreCase("U.K.G"))
					.map(e -> e.getTransportationFee()).reduce(0F, Float::sum);
			Float totalTutionFeeU = fees.stream()
					.filter(f -> f.getStudent().getGrade().getName().equalsIgnoreCase("U.K.G"))
					.map(e -> e.getTutionFee()).reduce(0F, Float::sum);
			Float totalTransportFeePaidU = fees.stream()
					.filter(f -> f.getStudent().getGrade().getName().equalsIgnoreCase("U.K.G"))
					.map(e -> e.getPaidTransportFee()).reduce(0F, Float::sum);
			Float totalTutionFeePaidU = fees.stream()
					.filter(f -> f.getStudent().getGrade().getName().equalsIgnoreCase("U.K.G"))
					.map(e -> e.getPaidTutionFee()).reduce(0F, Float::sum);
			nursheryFees.setGradeName("NURSHERY");
			nursheryFees.setTotalTransportFee(totalTransportFeeN.longValue());
			nursheryFees.setTotalTutionFee(totalTutionFeeN.longValue());
			nursheryFees.setTotalTransportFeePaid(totalTransportFeePaidN.longValue());
			nursheryFees.setTotalTutionFeepaid(totalTutionFeePaidN.longValue());
			feeReports.add(nursheryFees);

			ukgFees.setGradeName("L.K.G");
			ukgFees.setTotalTransportFee(totalTransportFeeL.longValue());
			ukgFees.setTotalTutionFee(totalTutionFeeNL.longValue());
			ukgFees.setTotalTransportFeePaid(totalTransportFeePaidNL.longValue());
			ukgFees.setTotalTutionFeepaid(totalTutionFeePaidNL.longValue());
			feeReports.add(ukgFees);

			lkgFees.setGradeName("U.K.G");
			lkgFees.setTotalTransportFee(totalTransportFeeU.longValue());
			lkgFees.setTotalTutionFee(totalTutionFeeU.longValue());
			lkgFees.setTotalTransportFeePaid(totalTransportFeePaidU.longValue());
			lkgFees.setTotalTutionFeepaid(totalTutionFeePaidU.longValue());
			feeReports.add(lkgFees);
			schoolVO.setFeesReport(feeReports);

		}

		return schoolVO;
	}

	@Override
	public SchoolReportVO getAssignmentReportForSchool(Integer studentId, Date toDate, Date fromDate) {
		SchoolReportVO schoolVO = new SchoolReportVO();
		List<AssignmentReportVO> assignmentReport = new ArrayList<>();
		AssignmentReportVO nur = new AssignmentReportVO();
		nur.setGradeName("NURSHERY");
		nur.setNoOfVideosViewed(5);
		nur.setNoOfWorkSheetAppeared(7);
		nur.setNoOfQuestionsAppeared(10);
		nur.setNoOfCorrectAnswers(8);
		assignmentReport.add(nur);
		AssignmentReportVO lkg = new AssignmentReportVO();
		lkg.setNoOfVideosViewed(5);
		lkg.setNoOfWorkSheetAppeared(8);
		lkg.setNoOfQuestionsAppeared(10);
		lkg.setNoOfCorrectAnswers(4);
		lkg.setGradeName("L.K.G");
		assignmentReport.add(lkg);
		AssignmentReportVO ukg = new AssignmentReportVO();
		ukg.setNoOfVideosViewed(12);
		ukg.setNoOfWorkSheetAppeared(7);
		ukg.setNoOfQuestionsAppeared(10);
		ukg.setNoOfCorrectAnswers(10);
		ukg.setGradeName("U.K.G");
		assignmentReport.add(ukg);
		schoolVO.setAssignmentReport(assignmentReport);
		return schoolVO;
	}

	@Override
	public MobileReportVO getMobileReportVO(int studentId, String fromDate, String toDate) {
		Student s = null;
		int gradeId = 0;
		if (studentId != 0) {
			s = studentRepository.findById(studentId);
			gradeId = s.getGrade().getId();
		}
		final int fGrade = gradeId;
		MobileReportVO report = new MobileReportVO();
		List<Result> results = resultRepository.getResultByStudentId(studentId);
		List<Tracker> tracker = trackerRepository.getTrackingDataForStudent(studentId);
		List<Assignment> assignments = assignmentRepo.getAssignmentsBySchool(s.getSchool().getId());
		if (assignments != null && !assignments.isEmpty()) {
			report.setTotalNoOfAssignment(assignments.stream().filter(t -> t.getGrade().getId() == fGrade)
					.collect(Collectors.toList()).size());
		} else {
			report.setTotalNoOfAssignment(0);
		}
		StudentFeeVO stVo = new StudentFeeVO();
		stVo.setId(studentId);
		if (results != null && !results.isEmpty()) {
			List<Result> l = results.stream().filter(r -> r.getResult()).collect(Collectors.toList());
			report.setNoOfQuestionFaced(results.size());
		} else {
			report.setNoOfQuestionFaced(0);
		}
		if (tracker != null) {
			Long noOfVid = tracker.stream().filter(t -> t.getTrackingType().equalsIgnoreCase(TrackerServiceImpl.VIDEO))
					.count();
			Long noOfWsheet = tracker.stream()
					.filter(t -> t.getTrackingType().equalsIgnoreCase(TrackerServiceImpl.WORKSHEET)).count();
			report.setNoOfVideosWatched(noOfVid.intValue());
			report.setNoOfWorksheetAppeared(noOfWsheet.intValue());

		} else {
			report.setNoOfVideosWatched(0);
			report.setNoOfWorksheetAppeared(0);

		}
		return report;
	}

}
