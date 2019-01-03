package com.yooyoo.service;

import java.util.List;
import com.yooyoo.model.School;
import com.yooyoo.vo.SchoolInfo;

public interface SchoolService {

	public void saveSchool(School school);

	public School editSchool(int id);

	public boolean deleteSchool(int id);

	public List<School> loadSchool();
	
	public School getSchoolById(int id);

	public void uploadSchoolCsv(List<SchoolInfo> schools);
}
