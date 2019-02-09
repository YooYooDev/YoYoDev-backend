package com.yooyoo.service;

import com.yooyoo.model.Category;
import com.yooyoo.model.Subject;

public interface CurriculumService {
	void saveSubject(Subject subject);

	void saveCategory(Category category);
}
