package com.yooyoo.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yooyoo.model.Category;
import com.yooyoo.model.Subject;
import com.yooyoo.repository.CategoryRepository;
import com.yooyoo.repository.SubjectRepository;
import com.yooyoo.service.CurriculumService;
@Service
public class CurriculumServiceImpl implements CurriculumService{

	@Autowired
	private SubjectRepository subjectRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	
	@Override
	public void saveSubject(Subject subject) {
		subjectRepo.save(subject);
	}

	@Override
	public void saveCategory(Category category) {
		categoryRepo.save(category);
	}

}
