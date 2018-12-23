package com.yooyoo.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.yooyoo.model.School;
import com.yooyoo.model.Student;

public interface SchoolRepository extends CrudRepository<School, Integer>{
	public School findById(long id);
	
	@Query("select s from School AS s")
	public Set<School> getAllSchool();
	
}
