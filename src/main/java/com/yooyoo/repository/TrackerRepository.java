package com.yooyoo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yooyoo.model.Tracker;

@Repository
public interface TrackerRepository extends CrudRepository<Tracker, Integer> {

	public static final String GET_TRACKER_FOR_VALIDATION = "select t from Tracker t where t.studentId = :studentId and t.assignmentId = :assignmentId and t.trackingType = :trackingType ";

	public static final String GET_TRACKER_FOR_STUDENT_REPORT = "select t from Tracker t where t.studentId = :studentId ";

	public static final String GET_TRACKER_FOR_SCHOOL_REPORT = "select t from Tracker t where t.schoolId = :schoolId ";

	@Query(GET_TRACKER_FOR_VALIDATION)
	public List<Tracker> getTrackerModelForStudent(@Param("studentId") Integer studentId,
			@Param("assignmentId") Integer assignmentId, @Param("trackingType") String trackingType);

	@Query(GET_TRACKER_FOR_STUDENT_REPORT)
	public List<Tracker> getTrackingDataForStudent(@Param("studentId") Integer studentId);

	@Query(GET_TRACKER_FOR_SCHOOL_REPORT)
	public List<Tracker> getTrackingDataForSchool(@Param("schoolId") Integer schoolId);


}
