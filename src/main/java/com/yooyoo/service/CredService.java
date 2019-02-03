package com.yooyoo.service;

import java.util.List;

import com.yooyoo.vo.CredManagerVO;
import com.yooyoo.vo.ResultVO;

public interface CredService {
	
	public ResultVO save(CredManagerVO credVo);
	
	public void update(CredManagerVO credVo);
	
	public CredManagerVO getCredManager(int id);
	
	public ResultVO deleteCredManager(int id);
	
	public List<CredManagerVO> getAllCredManager();
	
	public List<CredManagerVO> getCredManagerBySchool(int schoolId);

}
