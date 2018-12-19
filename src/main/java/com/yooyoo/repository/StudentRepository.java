package com.yooyoo.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yooyoo.model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer>{
	
	public static String GET_STUDENTS_BY_SCHOOL = "select s from Student s where s.school.id = :schoolId ";
	@Query(GET_STUDENTS_BY_SCHOOL)
	public Set<Student> getStudentsBySchool(@Param("schoolId") Integer schoolId);
	

}
