package com.yooyoo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yooyoo.model.Grade;
import com.yooyoo.model.School;
import com.yooyoo.model.Student;
import com.yooyoo.repository.GradeRepository;
import com.yooyoo.repository.SchoolRepository;
import com.yooyoo.repository.StudentRepository;
import com.yooyoo.service.StudentService;
import com.yooyoo.util.VOMapper;
import com.yooyoo.vo.StudentVO;

@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	SchoolRepository schoolRepository;
	
	@Autowired
	GradeRepository gradeRepository;

	@Override
	public void saveStudent(StudentVO student) {
		
		Student s = VOMapper.getStudent(student);
		School school = schoolRepository.findById(student.getSchoolId());
		Grade grade = gradeRepository.findById(student.getGradeId());
		s.setSchool(school);
		s.setGrade(grade);
		studentRepository.save(s);
	}

	@Override
	public StudentVO updateStudent(StudentVO student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteStudent(int studentId) {
		Student student = studentRepository.findById(studentId);
		studentRepository.delete(student);
		
	}

	@Override
	public List<StudentVO> getAllStudentsBySchool(int schoolId) {
		Set<Student> students = studentRepository.getStudentsBySchool(schoolId);
		List<StudentVO> studentsVos = new ArrayList<>();
		for(Student student :students){
			StudentVO vo = new StudentVO();
			vo=VOMapper.getStudent(student);
			studentsVos.add(vo);
		}
		return studentsVos;
	}

	@Override
	public List<StudentVO> getStudentsBySchoolAndClass(StudentVO studentVo) {
		int schoolId = studentVo.getSchoolId();
		int gradeId = studentVo.getGradeId();
		Set<Student> students = studentRepository.getStudentsBySchoolAndClass(schoolId, gradeId);
		List<StudentVO> studentsVos = new ArrayList<>();
		for(Student student :students){
			StudentVO vo = new StudentVO();
			vo=VOMapper.getStudent(student);
			studentsVos.add(vo);
		}
		return studentsVos;
	}

}
