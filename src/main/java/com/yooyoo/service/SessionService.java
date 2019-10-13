package com.yooyoo.service;

import com.yooyoo.vo.ResultVO;

public interface SessionService {
	
	boolean findSessionByToken(String accessToken);

	ResultVO removeSession(String token);

}
