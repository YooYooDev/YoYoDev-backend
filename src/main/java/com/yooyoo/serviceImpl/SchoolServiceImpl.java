package com.yooyoo.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yooyoo.controller.SchoolController;
import com.yooyoo.model.School;
import com.yooyoo.repository.SchoolRepository;
import com.yooyoo.service.SchoolService;
import com.yooyoo.vo.SchoolInfo;

@Service
public class SchoolServiceImpl implements SchoolService{

	Logger logger = LoggerFactory.getLogger(SchoolServiceImpl.class);
	@Autowired
	public SchoolRepository schoolRepo;
	
	@Override
	public void saveSchool(School school) {
		schoolRepo.save(school);
	}

	@Override
	public School editSchool(long id) {
		School school=schoolRepo.findById(id);
		logger.info("School Details "+school.getCode());
		return school;
	}

	@Override
	public boolean deleteSchool(long id) {
		School school=schoolRepo.findById(id);
		boolean deleteStatus= false;
		if(null!=school) {
			//delete school
			schoolRepo.delete(school);
			logger.info("School has been deleted ");
			deleteStatus=true;
		}else {
			logger.info("No School Found");
		}
		return deleteStatus;		
	}

	@Override
	public Set<School> loadSchool() {
		logger.info("load SchoolDB hit... ");
		Set<School> schoolList= schoolRepo.getAllSchool();
		/*Set<School> schoolList = new HashSet<School>();
		for (School schl : schoolRepo.findAll()) {
			schoolList.add(schl);
			logger.info("school details from db "+schl.getId());
		}*/
		return schoolList;
	}

}
