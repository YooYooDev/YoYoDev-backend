package com.yooyoo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yooyoo.model.Category;
import com.yooyoo.model.Subject;
import com.yooyoo.model.Topic;
import com.yooyoo.repository.CategoryRepository;
import com.yooyoo.repository.SubjectRepository;
import com.yooyoo.repository.TopicRepository;
import com.yooyoo.service.CurriculumService;
import com.yooyoo.vo.ResultVO;

@Service
public class CurriculumServiceImpl implements CurriculumService {

	@Autowired
	private SubjectRepository subjectRepo;

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private TopicRepository topicRepo;

	@Override
	public void saveSubject(Subject subject) {
		subjectRepo.save(subject);
	}

	@Override
	public void saveCategory(Category category) {
		try {
			categoryRepo.save(category);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public List<Subject> getAllSubjects() {
		List<Subject> subs = new ArrayList<>();
		Iterable<Subject> subjects = subjectRepo.findAll();
		for (Subject s : subjects) {
			subs.add(s);
		}
		return subs;
	}

	@Override
	public List<Category> getAllCategories() {
		List<Category> cats = new ArrayList<>();
		Iterable<Category> catrgories = categoryRepo.findAll();
		for (Category s : catrgories) {
			cats.add(s);
		}
		return cats;
	}

	@Override
	public void deleteCategory(Category category) {
		categoryRepo.delete(category);

	}

	@Override
	public void deleteSubject(Subject subject) {

		subjectRepo.delete(subject);

	}

	@Override
	public ResultVO createTopic(Topic topic) {
		ResultVO result = new ResultVO();
		if (topic.getName() != null && !(topicRepo.getTopicByName(topic.getName()) != null)) {
			topicRepo.save(topic);
			result.setMessage("Record Saved Sucessfully ...");
			result.setStatus(200);
		} else {
			result.setMessage("Topic Exists with given name...");
			result.setStatus(400);

		}
		return result;
	}

	@Override
	public void deleteTopic(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public ResultVO updateTopic(Topic topic) {
		ResultVO result = new ResultVO();
		Topic topic0 = getTopicById(topic.getId());
		if (topic0 != null) {
			Topic topic1 = topicRepo.getTopicByName(topic.getName());
			if (topic1 == null) {
				topicRepo.save(topic);
				result.setMessage("Record updated Sucessfully ...");
				result.setStatus(200);
			} else {
				result.setMessage("Topic Exists with given name...");
				result.setStatus(400);
			}
		} else {
			result.setMessage("Topic not found...");
			result.setStatus(404);
		}
		return result;
	}

	@Override
	public Topic getTopicById(Integer id) {
		Optional<Topic> topc = topicRepo.findById(id);
		return topc.get();
	}

	@Override
	public List<Topic> getAllTopics() {
		List<Topic> result = new ArrayList<>();
		Iterable<Topic> topics = topicRepo.findAll();
		for (Topic t : topics) {
			result.add(t);
		}
		return result;
	}

}
