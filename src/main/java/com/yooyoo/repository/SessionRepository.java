package com.yooyoo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.yooyoo.model.SessionManager;

public interface SessionRepository extends CrudRepository<SessionManager, Integer>{
	
	public static String GET_SESSION_BY_ACCESSTOKEN = "select s from SessionManager s where s.sessionId = :accessToken";
	
	public static String GET_SESSION_BY_USERID= "select s from SessionManager s where s.userId = :userId";
	
	@Query(GET_SESSION_BY_ACCESSTOKEN)
	public SessionManager getSessionByToken(@Param("accessToken") String sessionId);

	@Query(GET_SESSION_BY_USERID)
	public SessionManager findByUserId(@Param("userId") Integer studentId);
	
	

}
