package com.yooyoo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.yooyoo.model.Result;

public interface ResultRepository extends CrudRepository<Result, Integer>{
	
public static String GET_RESULT_BY_STUDENT_ID = "select r from Result r where r.studentId = :studentId";

public static String GET_RESULT_BY_SCHOOL_ID = "select r from Result r where r.schoolId = :schoolId";
	
	@Query(GET_RESULT_BY_STUDENT_ID)
	public List<Result> getResultByStudentId(@Param("studentId") Integer studentId);
	
	
	@Query(GET_RESULT_BY_SCHOOL_ID)
	public List<Result> getResultBySchoolId(@Param("schoolId") Integer schoolId);


}
