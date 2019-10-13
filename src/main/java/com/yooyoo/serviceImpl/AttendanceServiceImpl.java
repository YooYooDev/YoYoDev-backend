package com.yooyoo.serviceImpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yooyoo.model.Attendance;
import com.yooyoo.model.Grade;
import com.yooyoo.model.School;
import com.yooyoo.model.Student;
import com.yooyoo.repository.AttendanceRepository;
import com.yooyoo.repository.GradeRepository;
import com.yooyoo.repository.StudentRepository;
import com.yooyoo.service.AttendanceService;
import com.yooyoo.vo.AttendanceVO;
import com.yooyoo.vo.MobileAttendanceVO;
import com.yooyoo.vo.StudentDTO;

@Service
public class AttendanceServiceImpl implements AttendanceService {
	Logger logger = LoggerFactory.getLogger(AttendanceServiceImpl.class);

	@Autowired
	public AttendanceRepository attendanceRepository;

	@Autowired
	public StudentRepository studentRepo;
	
	@Autowired
	public GradeRepository gradeRepo;

	@Override
	public boolean save(AttendanceVO attendanceDetails) {
		logger.info("Attendance Save Method Hit");
		DateFormat formatter;
		Date date = null;
		List<StudentDTO> students = attendanceDetails.getStudentList();
		for (StudentDTO student : students) {
			if (!checkIfAttendanceTaken(student)) {
				Attendance attendance = new Attendance();
				if (attendanceDetails.getId() != null) {
					attendance.setId(attendanceDetails.getId());
				}
				Grade grade = new Grade();
				grade.setId(attendanceDetails.getGrade());
				School school = new School();
				school.setId(attendanceDetails.getSchoolId());
				Student studentDetails = new Student();
				studentDetails.setId(student.getStudentId());
				attendance.setSchool(school);
				attendance.setGrade(grade);
				formatter = new SimpleDateFormat("dd-MMM-yy");
				try {
					date = formatter.parse(attendanceDetails.getDate());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				attendance.setAttend_date(date);
				attendance.setStudent(studentDetails);
				attendance.setStatus(student.isAttendanceStatus() ? 1 : 0);
				attendance.setCreated_at(new Date());
				attendance.setUpdatedd_at(new Date());
				attendance.setDeleted("N");
				logger.info("DISPLAY attendance " + attendance);
				attendanceRepository.save(attendance);
			}else{
				List<Attendance> atts = attendanceRepository.getCurrentDayAttendanceForStudent(student.getStudentId());
				if (atts.size() > 0) {
					Attendance a = atts.get(atts.size() - 1);
					a.setStatus(student.isAttendanceStatus() ? 1 : 0);
					attendanceRepository.save(a);
				}
			}
		}
		return true;
	}

	private boolean checkIfAttendanceTaken(StudentDTO student) {
		boolean havetaken = false;
		List<Attendance> atts = attendanceRepository.getCurrentDayAttendanceForStudent(student.getStudentId());
		if (atts != null && !atts.isEmpty()) {
			havetaken = true;
		}
		return havetaken;
	}

	@Override
	public List<AttendanceVO> getAttandanceDetails(int schoolId) {
		Set<Attendance> attts = attendanceRepository.getAttendanses(schoolId);
		List<AttendanceVO> ats = new ArrayList<>();
		for (Attendance a : attts) {
			AttendanceVO vo = new AttendanceVO();
			vo.setStudentName(a.getStudent().getFirst_name());
			vo.setDate(a.getAttend_date().toString());
			vo.setGrade(a.getGrade().getId());
			vo.setStatus(a.getStatus());
			vo.setStudentId(a.getStudent().getId());
			vo.setId(a.getId());
			ats.add(vo);
		}
		return ats;
	}

	@Override
	public MobileAttendanceVO getAttendancesByUseridAndMonth(int userId, Date fromDate, Date toDate) {
		MobileAttendanceVO mobileAttendance = new MobileAttendanceVO();
		List<Attendance> attendances = attendanceRepository.getAttendansesForUser(userId, fromDate, toDate);
		Student s = studentRepo.findById(userId);
		mobileAttendance.setStudentid(s.getId());
		mobileAttendance.setStudentName(s.getFirst_name());
		if (attendances != null && !attendances.isEmpty()) {
			mobileAttendance.setAttendanceTakenDays(attendances.size());
			mobileAttendance.setPresentDays(
					attendances.stream().filter(r -> r.getStatus() == 1).collect(Collectors.toList()).size());
		} else {
			mobileAttendance.setAttendanceTakenDays(0);
			mobileAttendance.setPresentDays(0);
		}

		return mobileAttendance;
	}

	@Override
	public AttendanceVO getAttandanceDetailsBySchoolAndGrade(int schoolId, String gradeCode) {
		DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		Grade gra = gradeRepo.getGradebyName(gradeCode);
		Set<Student> students = studentRepo.getStudentsBySchoolAndClass(schoolId, gra.getId());
		List<StudentDTO> sDto = new ArrayList<>();
		AttendanceVO vo = new AttendanceVO();
		vo.setDate(formatter.format(new Date()));
		vo.setSchoolId(schoolId);
		vo.setGrade(gra.getId());
		for (Student s : students) {
			StudentDTO s1 = new StudentDTO();
			s1.setStudentId(s.getId());
			s1.setStudentName(s.getFirst_name());
			List<Attendance> atts = attendanceRepository.getCurrentDayAttendanceForStudent(s.getId());
			if (atts.size() > 0) {
				int i = atts.size() - 1;
				System.out.println("Size ids :::"+i);
				Attendance a = atts.get(i);
				s1.setAttendanceStatus(a.getStatus() == 1 ? true : false);
			} else {
				s1.setAttendanceStatus(false);
			}

			sDto.add(s1);

		}
		vo.setStudentList(sDto);

		return vo;

	}
}
