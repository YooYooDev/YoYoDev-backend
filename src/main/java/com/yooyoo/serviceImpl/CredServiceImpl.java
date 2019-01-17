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

@Service
public class CredServiceImpl implements CredService{
	
	@Autowired
	CredManagerRepository repository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	SchoolRepository schoolRepository;

	@Override
	public void save(CredManagerVO credVo) {
		CredManager manager = VOMapper.getCredManagerModel(credVo);
		Optional<Role> role = roleRepository.findById(credVo.getRoleId());
		manager.setRole(role.get());
		Optional<School> school = schoolRepository.findById(credVo.getSchoolId());
		manager.setSchool(school.get());
		manager.setPassword("Welcome@123");
		repository.save(manager);
		
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
	public void deleteCredManager(int id) {
		// TODO Auto-generated method stub
		
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
