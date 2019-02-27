package com.yooyoo.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yooyoo.model.Assignment;


@Repository
public interface AssignmentRepository extends CrudRepository<Assignment, Integer>{
	public static String GET_ASSIGNMENTS_BY_SCHOOL = "select a from Assignment a where a.school.id = :schoolId ORDER BY updatedDate DESC";
	
	@Query(GET_ASSIGNMENTS_BY_SCHOOL)
	public Set<Assignment> getAssignmentsBySchool(@Param("schoolId") Integer schoolId);

}
