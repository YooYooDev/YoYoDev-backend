package com.yooyoo.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yooyoo.model.CredManager;

@Repository
public interface CredManagerRepository extends CrudRepository<CredManager, Integer> {
	public static String GET_USER_DETAILS = "select c from CredManager c where c.email = :email and c.password = :password ";
	
	public static String GET_USER_DETAILS_BY_EMAIL = "select c from CredManager c where c.email = :email ";
	
	public static String GET_USER_DETAILS_BY_USERNAME = "select c from CredManager c where c.user_name = :userName ";
	
	
	@Query(GET_USER_DETAILS)
	public CredManager getUserDetails(@Param("email") String email, @Param("password") String password);
	
	@Query(GET_USER_DETAILS_BY_EMAIL)
	public CredManager getUserDetailsByEmail(@Param("email") String email);
	
	@Query(GET_USER_DETAILS_BY_USERNAME)
	public CredManager getUserDetailsByUserName(@Param("userName") String userName);
	
	
	@Query("select c from CredManager AS c")
	public Set<CredManager> getAllCreds();

}
