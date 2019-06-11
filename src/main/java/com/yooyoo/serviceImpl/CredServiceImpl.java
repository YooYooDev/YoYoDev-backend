package com.yooyoo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yooyoo.model.CredManager;
import com.yooyoo.model.Role;
import com.yooyoo.model.School;
import com.yooyoo.repository.CredManagerRepository;
import com.yooyoo.repository.RoleRepository;
import com.yooyoo.repository.SchoolRepository;
import com.yooyoo.service.CredService;
import com.yooyoo.util.VOMapper;
import com.yooyoo.vo.CredManagerVO;
import com.yooyoo.vo.ResultVO;

@Service
public class CredServiceImpl implements CredService{
	
	@Autowired
	CredManagerRepository repository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	SchoolRepository schoolRepository;

	@Override
	public ResultVO save(CredManagerVO credVo) {
		ResultVO result = new ResultVO();
		if(!validateEmailAndUserName(credVo)){
		CredManager manager = VOMapper.getCredManagerModel(credVo);
		Optional<Role> role = roleRepository.findById(credVo.getRoleId());
		manager.setRole(role.get());
		Optional<School> school = schoolRepository.findById(credVo.getSchoolId());
		manager.setSchool(school.get());
		manager.setPassword(credVo.getPassword());
		repository.save(manager);
		result.setStatus(200);
		result.setMessage("User Created Sucessfully...");
		}else{
			result.setStatus(400);
			result.setMessage("duplicate username or email...");
		}
		return result;
	}

	private boolean validateEmailAndUserName(CredManagerVO credVo) {
		boolean status = false;
		CredManager manager = null;
		if(credVo.getUserName() != null){
			manager = repository.getUserDetailsByUserName(credVo.getUserName());
		}else if(credVo.getEmail() != null){
			manager = repository.getUserDetailsByUserName(credVo.getEmail());
		}
		if(manager != null){
			status = true;
		}
		return status;
	}

	@Override
	public void update(CredManagerVO credVo) {
		CredManager manager = VOMapper.getCredManagerModel(credVo);
		Optional<Role> role = roleRepository.findById(credVo.getRoleId());
		manager.setRole(role.get());
		Optional<School> school = schoolRepository.findById(credVo.getSchoolId());
		manager.setSchool(school.get());
		repository.save(manager);
		
	}

	@Override
	public CredManagerVO getCredManager(int id) {
		Optional<CredManager> cred = repository.findById(id);
		CredManagerVO vo = VOMapper.getCredManagerVO(cred.get());
		return vo;
	}

	@Override
	public ResultVO deleteCredManager(int id) {
		ResultVO vo = new ResultVO();
		Optional<CredManager> cred = repository.findById(id);
		repository.delete(cred.get());
		vo.setStatus(200);
		vo.setMessage("User has been deleted sucessfully...");
       return vo;
	}

	@Override
	public List<CredManagerVO> getAllCredManager() {
		List<CredManagerVO> creds = new ArrayList<>();
		Set<CredManager> users = repository.getAllCreds();
		for(CredManager user : users){
			CredManagerVO vo = VOMapper.getCredManagerVO(user);
			creds.add(vo);
		}
		return creds;
	}

	@Override
	public List<CredManagerVO> getCredManagerBySchool(int schoolId) {
		// TODO Auto-generated method stub
		return null;
	}

}
