package com.yooyoo.serviceImpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yooyoo.model.Attendance;
import com.yooyoo.model.Grade;
import com.yooyoo.model.School;
import com.yooyoo.model.Student;
import com.yooyoo.repository.AttendanceRepository;
import com.yooyoo.service.AttendanceService;
import com.yooyoo.vo.AttendanceVO;
import com.yooyoo.vo.StudentDTO;

@Service
public class AttendanceServiceImpl implements AttendanceService {
	Logger logger = LoggerFactory.getLogger(AttendanceServiceImpl.class);

	@Autowired
	public AttendanceRepository attendanceRepository;

	@Override
	public boolean save(AttendanceVO attendanceDetails) {
		logger.info("Attendance Save Method Hit");
		DateFormat formatter;
		Date date = null;

		for (StudentDTO student : attendanceDetails.getStudentList()) {
			if (null != student) {
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
			}
		}
		return true;
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
}
