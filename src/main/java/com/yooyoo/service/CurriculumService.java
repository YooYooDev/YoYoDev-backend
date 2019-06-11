package com.yooyoo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.yooyoo.model.Category;
import com.yooyoo.model.Subject;
import com.yooyoo.model.Topic;
import com.yooyoo.vo.ResultVO;

public interface CurriculumService {
	void saveSubject(Subject subject);

	void saveCategory(Category category);

	List<Subject> getAllSubjects();

	List<Category> getAllCategories();

	void deleteCategory(Category category);

	void deleteSubject(Subject subject);

	ResultVO createTopic(Topic topic);

	ResultVO updateTopic(Topic topic);

	Topic getTopicById(Integer id);

	List<Topic> getAllTopics();
	
	ResultVO deleteTopic(Integer id);

	Subject getSubjectById(Integer subjectId);

	List<Topic> getTopicBySubjectId(Integer id);
	
	byte[] getWorkSheetMedia(Integer topicId) throws Exception;
	
	void saveWorkSheetMedia(Integer topicId, MultipartFile mediaFile) throws Exception;

	ResultVO updateWorkSheetForTopic(Integer topicId, MultipartFile media, String url) throws Exception;
}
