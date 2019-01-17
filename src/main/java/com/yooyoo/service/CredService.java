package com.yooyoo.service;

import java.util.List;

import com.yooyoo.vo.CredManagerVO;

public interface CredService {
	
	public void save(CredManagerVO credVo);
	
	public void update(CredManagerVO credVo);
	
	public CredManagerVO getCredManager(int id);
	
	public void deleteCredManager(int id);
	
	public List<CredManagerVO> getAllCredManager();
	
	public List<CredManagerVO> getCredManagerBySchool(int schoolId);

}
