package com.yooyoo.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yooyoo.model.Assignment;


@Repository
public interface AssignmentRepository extends CrudRepository<Assignment, Integer> {
	public static String GET_ASSIGNMENTS_BY_SCHOOL = "select a from Assignment a where a.school.id = :schoolId ORDER BY updatedDate DESC";
	
	public static String GET_ASSIGNMENTS_BY_SCHOOL_AND_GRADE = "select a from Assignment a "
			+ "where a.school.id = :schoolId and a.grade.id = :gradeId and current_date() BETWEEN date and toDate ORDER BY updatedDate DESC";
	
	public static String GET_ASSIGNMENTS_BY_SCHOOL_AND_MONTH = "select a from Assignment a where a.school.id = :schoolId and to_char(a.date, 'MON') = :month";

	@Query(GET_ASSIGNMENTS_BY_SCHOOL)
	public Set<Assignment> getAssignmentsBySchool(@Param("schoolId") Integer schoolId);

	@Query(GET_ASSIGNMENTS_BY_SCHOOL_AND_GRADE)
	public List<Assignment> getAssignmentsBySchoolAndGrade(@Param("schoolId") Integer schoolId,
			@Param("gradeId") Integer gradeId);

	@Query(GET_ASSIGNMENTS_BY_SCHOOL_AND_MONTH)
	public List<Assignment> getAssignmentsBySchoolAndMonth(@Param("schoolId")Integer id, @Param("month")String month);

}
