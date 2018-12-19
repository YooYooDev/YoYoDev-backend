package com.yooyoo.util;

import com.yooyoo.model.School;
import com.yooyoo.model.Student;
import com.yooyoo.vo.SchoolInfo;
import com.yooyoo.vo.StudentVO;

public class VOMapper {
	
	public static Student getStudent(StudentVO studentVO){
		Student student = new Student();
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
		return student;
	}
	
	public static School getSchool(SchoolInfo schoolVO) {
		School school = new School();
		school.setId(schoolVO.getId());
		school.setName(schoolVO.getName());
		school.setCode(schoolVO.getCode());
		school.setAddress1(schoolVO.getAddress());
		school.setPost(schoolVO.getPost());
		school.setPin(schoolVO.getPin());
		school.setState(schoolVO.getState());
		school.setCountry(schoolVO.getCountry());
		school.setContact_person(schoolVO.getContactPerson());
		school.setRegistration_name(schoolVO.getRegistrationName());
		school.setEmail_id(schoolVO.getEmailId());
		school.setOwner_mobile(schoolVO.getOwnerMobile());
		school.setOwner_name(schoolVO.getOwnerName());
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
		return studentVO;
	}

}
