package com.yooyoo.service;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.yooyoo.vo.CredManagerVO;
import com.yooyoo.vo.ResultVO;

public interface CredService {
	
	public ResultVO save(CredManagerVO credVo);
	
	public void update(CredManagerVO credVo);
	
	public CredManagerVO getCredManager(int id);
	
	public ResultVO deleteCredManager(int id);
	
	public List<CredManagerVO> getAllCredManager();
	
	public List<CredManagerVO> getCredManagerBySchool(int schoolId);
	
	public void sendPasswordEmail(CredManagerVO credVo)throws AddressException, MessagingException, IOException, Exception;

}
