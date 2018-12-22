package com.yooyoo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yooyoo.model.School;
import com.yooyoo.model.Student;
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

	@Override
	public void saveStudent(StudentVO student) {
		
		Student s = VOMapper.getStudent(student);
		School school = schoolRepository.findById(student.getSchoolId());
		s.setSchool(school);
		studentRepository.save(s);
	}

	@Override
	public StudentVO updateStudent(StudentVO student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteStudent(int studentId) {
		Optional<Student> student = studentRepository.findById(studentId);
		studentRepository.delete(student.get());
		
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

}
