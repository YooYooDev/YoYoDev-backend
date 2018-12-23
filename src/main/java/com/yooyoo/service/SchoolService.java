package com.yooyoo.service;

import java.util.List;
import java.util.Set;

import com.yooyoo.model.School;
import com.yooyoo.vo.SchoolInfo;

public interface SchoolService {

	public void saveSchool(School school);

	public School editSchool(long id);

	public boolean deleteSchool(long id);

	public Set<School> loadSchool();
}
