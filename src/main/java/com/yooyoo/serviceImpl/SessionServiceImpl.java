package com.yooyoo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yooyoo.model.SessionManager;
import com.yooyoo.repository.SessionRepository;
import com.yooyoo.service.SessionService;
import com.yooyoo.vo.ResultVO;

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

	@Override
	public ResultVO removeSession(String token) {
		ResultVO vo = new ResultVO();
		SessionManager session = sessionRepository.getSessionByToken(token);
		if (session != null) {
			sessionRepository.delete(session);
			vo.setSessioninvalidated(true);
			vo.setStatus(200);
			vo.setMessage("Loggedout Sucessfully");
		}else{
			vo.setSessioninvalidated(false);
			vo.setStatus(404);
			vo.setMessage("Not Found");
		}
		return vo;
	}

}
