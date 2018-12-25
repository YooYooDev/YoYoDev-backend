package com.yooyoo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.yooyoo.model.Notifications;
import com.yooyoo.model.School;
import com.yooyoo.model.Student;
import com.yooyoo.vo.NotificationsVO;
import com.yooyoo.vo.SchoolInfo;
import com.yooyoo.vo.StudentVO;

public class VOMapper {
	
	static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	
	public static Student getStudent(StudentVO studentVO){
		Student student = new Student();
		student.setId(studentVO.getId());
		student.setFirst_name(studentVO.getFirstName());
		student.setMiddle_name(studentVO.getMiddleName());
		student.setLast_name(studentVO.getLastName());
		student.setRole("STUDENT");
		//student.setSchool(getSchool(studentVO.getSchool()));
		student.setP_email(studentVO.getPrimaryEmail());
		student.setS_email(studentVO.getSecondaryEmail());
		student.setParent_mobile1(studentVO.getParentMobile1());
		student.setParent_mobile2(studentVO.getParentMobile2());
		student.setPassword("password");
		student.setDeleted("N");
		//new fields
		try {
			student.setDob(simpleDateFormat.parse(studentVO.getDob()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		student.setPhoto(studentVO.getPhoto());
		student.setGender(studentVO.getGender());
		student.setFatherName(studentVO.getFatherName());
		student.setMotherName(studentVO.getMotherName());
		student.setFatherProfession(studentVO.getFatherProfession());
		student.setMotherProfession(studentVO.getMotherProfession());
		student.setAddress(studentVO.getAddress());
		student.setCity(studentVO.getCity());
		student.setPinCode(studentVO.getPinCode());
		return student;
	}
	
	public static School getSchool(SchoolInfo schoolVO) {
		School school = new School();
		school.setId(schoolVO.getId());
		school.setName(schoolVO.getName());
		school.setCode(schoolVO.getCode());
		school.setAddress(schoolVO.getAddress());
		school.setPost(schoolVO.getPost());
		school.setPin(schoolVO.getPin());
		school.setState(schoolVO.getState());
		school.setCountry(schoolVO.getCountry());
		school.setContactPerson(schoolVO.getContactPerson());
		school.setRegistrationName(schoolVO.getRegistrationName());
		school.setEmailId(schoolVO.getEmailId());
		school.setOwnerMobile(schoolVO.getOwnerMobile());
		school.setOwnerName(schoolVO.getOwnerName());
		return null;
	}

	public static StudentVO getStudent(Student student){
		StudentVO studentVO = new StudentVO();
		studentVO.setId(student.getId());
		studentVO.setFirstName(student.getFirst_name());
		studentVO.setLastName(student.getLast_name());
		studentVO.setMiddleName(student.getMiddle_name());
		studentVO.setRole("STUDENT");
		studentVO.setPrimaryEmail(student.getP_email());
		studentVO.setSecondaryEmail(student.getS_email());
		studentVO.setParentMobile1(student.getParent_mobile1());
		studentVO.setParentMobile2(student.getParent_mobile2());
		studentVO.setDeleted("N");
		
		//new fields
		//new fields
		studentVO.setDob(student.getDob()!= null?student.getDob().toString():"date");
		studentVO.setPhoto(student.getPhoto());
		studentVO.setGender(student.getGender());
		studentVO.setFatherName(student.getFatherName());
		studentVO.setMotherName(student.getMotherName());
		studentVO.setFatherProfession(student.getFatherProfession());
		studentVO.setMotherProfession(student.getMotherProfession());
		studentVO.setAddress(student.getAddress());
		studentVO.setCity(student.getCity());
		studentVO.setPinCode(student.getPinCode());
		return studentVO;
	}

	public static Notifications getNotificationModel(NotificationsVO notificationVO) {
		Notifications noti = new Notifications();
		noti.setHeader(notificationVO.getHeader());
		noti.setMessage(notificationVO.getMessage());
		noti.setCreated_at(new Date());
		noti.setUpdatedd_at(new Date());
		return noti;
	}

	public static NotificationsVO getNotificationVO(Notifications not) {
		NotificationsVO notVo = new NotificationsVO();
		notVo.setId(not.getId());
		notVo.setHeader(not.getHeader());
		notVo.setMessage(not.getMessage());
		notVo.setSchoolId(not.getSchool().getId());
		notVo.setGradeId(not.getGrade().getId());
		notVo.setStudentId(not.getStudent().getId());
		notVo.setCreated_at(not.getCreated_at());
		return notVo;
	}

}
