package com.yooyoo.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yooyoo.model.Fees;

@Repository
public interface FeeRepository extends CrudRepository<Fees, Integer> {
	
	public static String GET_FEES_BY_SCHOOL = "select f from Fees f where f.school.id = :schoolId and f.deleted = 'N'";
	
	@Query(GET_FEES_BY_SCHOOL)
	public Set<Fees> getFeesBySchool(@Param("schoolId") Integer schoolId);

}
