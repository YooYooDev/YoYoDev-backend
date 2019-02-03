package com.yooyoo.service;

import java.util.List;

import com.yooyoo.vo.GradeVO;
import com.yooyoo.vo.ResultVO;
import com.yooyoo.vo.StudentVO;

public interface StudentService {
	
	public ResultVO saveStudent(StudentVO student);
	
	public ResultVO updateStudent(StudentVO student);
	
	public void deleteStudent(int studentId);
	
	public List<StudentVO> getAllStudentsBySchool(int schoolId);
	
	public List<StudentVO> getStudentsBySchoolAndClass(StudentVO studentVo);
	
	public List<GradeVO> getStudentsBySchoolAndGrade(int schoolId);
	
	public Boolean uploadUserCsv(List<StudentVO> students);

}
