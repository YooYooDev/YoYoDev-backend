package com.yooyoo.service;

import java.util.List;

import com.yooyoo.vo.StudentVO;

public interface StudentService {
	
	public void saveStudent(StudentVO student);
	
	public StudentVO updateStudent(StudentVO student);
	
	public void deleteStudent(int studentId);
	
	public List<StudentVO> getAllStudentsBySchool(int schoolId);
	
	public List<StudentVO> getStudentsBySchoolAndClass(StudentVO studentVo);

}
