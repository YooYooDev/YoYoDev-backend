package com.yooyoo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yooyoo.exception.ResourceNotFoundException;
import com.yooyoo.model.Grade;
import com.yooyoo.model.School;
import com.yooyoo.model.Student;
import com.yooyoo.repository.GradeRepository;
import com.yooyoo.repository.SchoolRepository;
import com.yooyoo.repository.StudentRepository;
import com.yooyoo.service.StudentService;
import com.yooyoo.util.VOMapper;
import com.yooyoo.vo.GradeVO;
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
		Optional<School> schoolOpt = schoolRepository.findById(student.getSchoolId());
		Grade grade = gradeRepository.findById(student.getGradeId());
		School school = schoolOpt.get();
		
		if(grade != null && school != null){
			s.setSchool(school);
			s.setGrade(grade);
			studentRepository.save(s);
		}else{
			throw new ResourceNotFoundException("Invalid School Or Grade values");
		}
	}

	@Override
	public StudentVO updateStudent(StudentVO student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteStudent(int studentId) {
		Student student = studentRepository.findById(studentId);
		student.setDeleted("Y");
		studentRepository.save(student);
		
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

	@Override
	public List<GradeVO> getStudentsBySchoolAndGrade(int schoolId) {
		Set<Student> students = studentRepository.getStudentsBySchool(schoolId);
		List<GradeVO> grades = new ArrayList<>();
		grades=VOMapper.formatStudentsPerGrade(new ArrayList<Student>(students));
		return grades;
	}

	@Transactional
	@Override
	public Boolean uploadUserCsv(List<StudentVO> students) {
		boolean status = false;
		try{
			if(students != null && !students.isEmpty()){
				for(StudentVO student:students){
					saveStudent(student);
				}
				status = true;
			}
		}catch(Exception e){
			status = false;
		}
		return status;
	}

}
