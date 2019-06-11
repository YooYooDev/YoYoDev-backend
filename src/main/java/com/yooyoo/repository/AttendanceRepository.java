package com.yooyoo.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yooyoo.model.Attendance;

@Repository
public interface AttendanceRepository extends CrudRepository<Attendance, Integer>{
	
	@Query("select a from Attendance AS a where a.school.id  = :schoolId and  (CURRENT_DATE-date(attend_date)) = 0")
	public Set<Attendance> getAttendanses(@Param("schoolId") Integer schoolId);
	
	
	@Query("select a from Attendance AS a where a.student.id  = :userId AND a.attend_date BETWEEN :fromDate and :toDate ")
	public List<Attendance> getAttendansesForUser(@Param("userId") Integer userId, @Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate);
	
	@Query("select a from Attendance AS a where a.school.id  = :schoolId")
	public List<Attendance> getAttendansesBySchool(@Param("schoolId") Integer schoolId);
	
	@Query("select a from Attendance AS a where a.student.id  = :userId")
	public List<Attendance> getAttendansesByStudent(@Param("userId") Integer userId);

}
