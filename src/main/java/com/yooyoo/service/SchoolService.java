package com.yooyoo.service;

import java.util.List;
import com.yooyoo.model.School;
import com.yooyoo.vo.ResultVO;
import com.yooyoo.vo.SchoolInfo;

public interface SchoolService {

	public ResultVO saveSchool(School school);

	public ResultVO editSchool(School school);

	public boolean deleteSchool(int id);

	public List<School> loadSchool();
	
	public School getSchoolById(int id);

	public void uploadSchoolCsv(List<SchoolInfo> schools);
}
