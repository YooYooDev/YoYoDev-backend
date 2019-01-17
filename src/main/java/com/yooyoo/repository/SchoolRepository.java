package com.yooyoo.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yooyoo.model.School;


@Repository
public interface SchoolRepository extends CrudRepository<School, Integer>{
	@Query("select s from School AS s ORDER BY updatedAt DESC")
	public Set<School> getAllSchool();

}
