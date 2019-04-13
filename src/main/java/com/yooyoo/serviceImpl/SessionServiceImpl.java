package com.yooyoo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yooyoo.model.SessionManager;
import com.yooyoo.repository.SessionRepository;
import com.yooyoo.service.SessionService;

@Service
public class SessionServiceImpl implements SessionService{
	
	@Autowired
	SessionRepository sessionRepository;

	@Override
	public boolean findSessionByToken(String accessToken) {
		boolean activeSession = false;
		SessionManager session = sessionRepository.getSessionByToken(accessToken);
		if (session != null) {
			activeSession = true;
		}

		return activeSession;
	}

}
