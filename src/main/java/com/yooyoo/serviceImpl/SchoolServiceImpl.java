package com.yooyoo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yooyoo.model.School;
import com.yooyoo.repository.SchoolRepository;
import com.yooyoo.service.SchoolService;
import com.yooyoo.vo.SchoolInfo;

@Service
public class SchoolServiceImpl implements SchoolService{

	@Autowired
	public SchoolRepository schoolRepo;
	
	@Override
	public void saveSchool(School school) {
		schoolRepo.save(school);
	}

	@Override
	public void editSchool(SchoolInfo school) {
		
	}

}
