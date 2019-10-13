package com.yooyoo.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yooyoo.model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer>{
	
	
	public static String GET_STUDENTS_BY_SCHOOL = "select s from Student s where s.school.id = :schoolId ORDER BY updatedd_at DESC";
	
	public static String GET_STUDENTS_BY_SCHOOL_AND_GRADE = "select s from Student s where"
			                         + " s.school.id = :schoolId and s.grade.id = :gradeId";
	
	public static String GET_STUDENTS_BY_NAME_EMAIL_MOBILE_SCHOOL = "select s from Student s where s.school.id = :schoolId "
			+ "AND s.first_name = :fname and s.p_email = :pEmail and s.parent_mobile1 = :pMobile";
	
	public static String GET_STUDENTS_BY_SCHOOL_EMAIL_PASSWORD = "select s from Student s where s.school.id = :schoolId "
			+ "AND s.password = :password  and s.p_email = :pemail";
	
	public static String GET_STUDENTS_BY_EMAI = "select s from Student s where  "
			+ "s.s_email = :pemail  or s.p_email = :pemail";
	
	
	@Query(GET_STUDENTS_BY_SCHOOL)
	public Set<Student> getStudentsBySchool(@Param("schoolId") Integer schoolId);
	
	@Query(GET_STUDENTS_BY_SCHOOL_AND_GRADE)
	public Set<Student> getStudentsBySchoolAndClass(@Param("schoolId") Integer schoolId,@Param("gradeId") Integer gradeId);
	
	
	@Query(GET_STUDENTS_BY_NAME_EMAIL_MOBILE_SCHOOL)
	public Student getStudentByFirstNameMobileEmail(@Param("schoolId") Integer schoolId, @Param("fname") String fname,
			@Param("pEmail") String pEmail, @Param("pMobile") String pMobile);
	
	public Student findById(int id);

	@Query(GET_STUDENTS_BY_SCHOOL_EMAIL_PASSWORD)
	public List<Student> getStudentDetailsBySchoolMobileAndPassword(@Param("schoolId") Integer schoolId,
			@Param("password") String password, @Param("pemail") String pEmail);

	
	@Query(GET_STUDENTS_BY_EMAI)
	public List<Student> getStudentDetailsByEmail( @Param("pemail") String pEmail);
	

}
