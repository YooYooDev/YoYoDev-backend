package com.yooyoo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yooyoo.model.Grade;


@Repository
public interface GradeRepository extends CrudRepository<Grade, Integer>{
	
	public Grade findById(int id);

}
