package com.yooyoo.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yooyoo.model.School;

@Repository
public interface SchoolRepository extends CrudRepository<School, Integer> {
	public static String GET_SCHOOL_BY_NAME_AND_REGNO = "select s from School s where s.name = :name and  s.registrationName = :regname";
	
	public static String GET_SCHOOL_BY_Code = "select s from School s where s.code = :code";

	@Query("select s from School AS s ORDER BY updatedAt DESC")
	public Set<School> getAllSchool();

	@Query(GET_SCHOOL_BY_NAME_AND_REGNO)
	public List<School> findSchoolByName(@Param("name") String name, @Param("regname") String regname);

	public School findByCode(@Param("code") Integer schoolCode);

}
