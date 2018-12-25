package com.yooyoo.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yooyoo.model.Notifications;

@Repository
public interface NotificationRepository extends CrudRepository<Notifications, Integer> {
	
	public static String GET_NOTIFICATION_DETAILS_BYSCHOOLID_AND_CLASSID = "select n from Notifications n where"
			+ " n.school.id = :schoolId and n.grade.id = :gradeId"
			+ " and (CURRENT_DATE-date(created_at)) <=15";
	
	public static String GET_NOTIFICATION_DETAILS_BYSCHOOLID_AND_CLASSID_AND_STUDENTID = "select n from Notifications n where"
			+ " n.school.id = :schoolId and n.grade.id = :gradeId and n.student.id = :studentId"
			+ " and (CURRENT_DATE-date(created_at)) <=15";
	
	public static String GET_NOTIFICATION_DETAILS = "select n from Notifications n where"
			+ " (CURRENT_DATE-date(created_at)) <=15";
	
	public static String GET_NOTIFICATION_DETAILS_BYSCHOOLID = "select n from Notifications n where"
			+ " n.school.id = :schoolId and (CURRENT_DATE-date(created_at)) <=15";

	public List<Notifications> findAll();

	@Query(GET_NOTIFICATION_DETAILS_BYSCHOOLID_AND_CLASSID)
	public Set<Notifications> getNotificationDetailsBySchoolIdAndClassId(@Param("schoolId") int schoolId,
			@Param("gradeId") int gradeId);
	
	@Query(GET_NOTIFICATION_DETAILS)
	public Set<Notifications> getNotificationDetailsBySchoolIdAndClassId();
	
	@Query(GET_NOTIFICATION_DETAILS_BYSCHOOLID)
	public Set<Notifications> getNotificationDetailsBySchoolId(@Param("schoolId") int schoolId);
	
	@Query(GET_NOTIFICATION_DETAILS_BYSCHOOLID_AND_CLASSID_AND_STUDENTID)
	public Set<Notifications> getNotificationDetailsBySchoolIdAndStudentId(@Param("schoolId") int schoolId,
			@Param("gradeId") int gradeId,@Param("studentId") int studentId);

}
