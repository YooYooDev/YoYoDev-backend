package com.yooyoo.service;

public interface SessionService {
	
	boolean findSessionByToken(String accessToken);

}
