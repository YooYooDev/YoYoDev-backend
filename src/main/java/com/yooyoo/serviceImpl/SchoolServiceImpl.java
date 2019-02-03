package com.yooyoo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yooyoo.model.School;
import com.yooyoo.repository.SchoolRepository;
import com.yooyoo.service.SchoolService;
import com.yooyoo.util.FileUtils;
import com.yooyoo.util.VOMapper;
import com.yooyoo.vo.ResultVO;
import com.yooyoo.vo.SchoolInfo;

@Service
public class SchoolServiceImpl implements SchoolService{

	Logger logger = LoggerFactory.getLogger(SchoolServiceImpl.class);
	@Autowired
	public SchoolRepository schoolRepository;
	
	@Override
	public ResultVO saveSchool(School school) {
		ResultVO vo = new ResultVO();
		if(!isSchoolExists(school)){
		school.setCode(FileUtils.gen());	
		schoolRepository.save(school);
		vo.setStatus(200);
		vo.setMessage("School saved sucessfully..");
		}else{
			logger.info("school exists with name :"+school.getName());
			vo.setStatus(400);
			vo.setMessage("school exists with name :"+school.getName());
		}
		return vo;
	}

	private boolean isSchoolExists(School school) {
		boolean isSchoolExists = false;
		List<School> sch = schoolRepository.findSchoolByName(school.getName(), school.getRegistrationName());
		if (sch != null && !sch.isEmpty()) {
			isSchoolExists = true;
		}
		return isSchoolExists;
	}

	@Override
	public ResultVO editSchool(School school) {
		ResultVO vo = new ResultVO();
		List<School> sch = schoolRepository.findSchoolByName(school.getName(), school.getRegistrationName());
		if (sch != null && !sch.isEmpty()) {
			School s  = sch.get(0);
			if(s.getId() == school.getId()){
				schoolRepository.save(school);
				vo.setStatus(200);
				vo.setMessage("School updated sucessfully..");
			}else{
				logger.info("school exists with name :"+school.getName());
				vo.setStatus(400);
				vo.setMessage("Modify school name or registration no:"+school.getName());
			}
		}else{
			schoolRepository.save(school);
			vo.setStatus(200);
			vo.setMessage("School updated sucessfully..");
		}
		return vo;
	}

	@Override
	public boolean deleteSchool(int id) {
		Optional<School> school=schoolRepository.findById(id);
		boolean deleteStatus= false;
		if(null!=school) {
			//delete school
			School sch= school.get();
			sch.setActive(0);
			schoolRepository.save(sch);
			logger.info("School has been deleted ");
			deleteStatus=true;
		}else {
			logger.info("No School Found");
		}
		return deleteStatus;		
	}

	@Override
	public List<School> loadSchool() {
		logger.info("load SchoolDB hit... ");
		Set<School> schoolList= schoolRepository.getAllSchool();
		List<School> schools = new ArrayList<School>();
		for (School schl : schoolList) {
			schools.add(schl);
			logger.info("school details from db "+schl.getId());
		}
		return schools;
	}

	@Override
	public School getSchoolById(int id) {
		Optional<School> school = schoolRepository.findById(id);
		return school.get();
	}

	@Override
	public void uploadSchoolCsv(List<SchoolInfo> schools) {
		if(schools !=null && !schools.isEmpty()){
			for(SchoolInfo school: schools){
				School csvSchool = VOMapper.getSchool(school);
				saveSchool(csvSchool);
			}
		}
		
	}

}
