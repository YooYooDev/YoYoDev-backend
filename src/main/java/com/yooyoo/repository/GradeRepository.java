package com.yooyoo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yooyoo.model.Grade;


@Repository
public interface GradeRepository extends CrudRepository<Grade, Integer>{
	
	public static String GET_GRADE_BY_NAME = "select g from Grade g where g.name = :gradeName";
	
	public Grade findById(int id);
	
	@Query(GET_GRADE_BY_NAME)
	public Grade getGradebyName(@Param("gradeName") String gradeName);
	
}
