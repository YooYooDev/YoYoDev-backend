package com.yooyoo.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yooyoo.model.Fees;

@Repository
public interface FeeRepository extends CrudRepository<Fees, Integer> {
	
	public static String GET_FEES_BY_SCHOOL = "select f from Fees f where f.school.id = :schoolId and f.deleted = 'N'";
	
	public static String GET_FEES_BY_STUDENT = "select f from Fees f where f.student.id = :studentId and f.deleted = 'N'";
	
	@Query(GET_FEES_BY_SCHOOL)
	public Set<Fees> getFeesBySchool(@Param("schoolId") Integer schoolId);

	
	@Query(GET_FEES_BY_STUDENT)
	public List<Fees> findByStudentId(@Param("studentId") Integer studentId);

}
