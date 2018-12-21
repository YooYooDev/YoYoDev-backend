package com.yooyoo.service;

import com.yooyoo.model.School;
import com.yooyoo.vo.SchoolInfo;

public interface SchoolService {

	public void saveSchool(School school);

	public void editSchool(SchoolInfo school);
}
