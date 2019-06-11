package com.yooyoo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.yooyoo.model.Category;
import com.yooyoo.model.Subject;
import com.yooyoo.model.Topic;
import com.yooyoo.repository.CategoryRepository;
import com.yooyoo.repository.SubjectRepository;
import com.yooyoo.repository.TopicRepository;
import com.yooyoo.service.CurriculumService;
import com.yooyoo.vo.ResultVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CurriculumServiceImpl implements CurriculumService {

	@Autowired
	private SubjectRepository subjectRepo;

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private TopicRepository topicRepo;
	
	@Value("${media.url}")
	private String mediaUrl;


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
			if(!s.getName().isEmpty()){
			subs.add(s);
			}
		}
		return subs;
	}

	@Override
	public List<Category> getAllCategories() {
		List<Category> cats = new ArrayList<>();
		Iterable<Category> catrgories = categoryRepo.findAll();
		for (Category s : catrgories) {
			if(!s.getName().isEmpty()){
			cats.add(s);
			}
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
	public ResultVO deleteTopic(Integer id) {
		ResultVO vo = new ResultVO();
		try{
		Topic topic = getTopicById(id);
		topicRepo.delete(topic);
		vo.setStatus(200);
		vo.setMessage("Record deleted sucessfully...");
		}catch(Exception e){
			vo.setStatus(400);
			vo.setMessage("not able to delete record ..");
		}
		return vo;
	}

	@Override
	public ResultVO updateTopic(Topic topic) {
		ResultVO result = new ResultVO();
		Topic topic0 = getTopicById(topic.getId());
		if (topic0 != null) {
			Topic topic1 = topicRepo.getTopicByName(topic.getName());
			if (!(topic0.getName().equalsIgnoreCase(topic.getName()) && topic0.getId() != topic1.getId())) {
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
		List<Topic> result = topicRepo.getAllTopics();
		if (result != null && !result.isEmpty()) {
			for (Topic t : result) {
				t.setWorkSheetImage(null);
				t.setQuizLink(mediaUrl + "getworksheetlink/" + t.getId()+"/?t="+System.currentTimeMillis());
			}
		}
		return result;
	}

	@Override
	public Subject getSubjectById(Integer subjectId) {
		Optional<Subject> sub = subjectRepo.findById(subjectId);
		return sub.get();
	}

	@Override
	public List<Topic> getTopicBySubjectId(Integer id) {
		return topicRepo.getAllTopicsBySubjectId(id);
		
	}
	
	@Override
	public void saveWorkSheetMedia(Integer topicId, MultipartFile mediaFile) throws Exception {
		// Normalize file name
		if (mediaFile != null) {
			String audioName = StringUtils.cleanPath(mediaFile.getOriginalFilename());
			if (audioName.contains("..")) {
				throw new Exception("Sorry! Filename contains invalid path sequence ");
			}

		}

		try {
			if (topicRepo.findById(topicId).isPresent() && (mediaFile != null)) {
				Topic topic = topicRepo.findById(topicId).get();
				if (topic != null) {
					topic.setWorkSheetImage(mediaFile.getBytes());
					topicRepo.save(topic);
				}

			}
		} catch (Exception ex) {
			log.error("Could not store file, Please try again!", ex);
		}

	}
	
	@Override
	public byte[] getWorkSheetMedia(Integer topicId) throws Exception {
		Topic topic = null;
		byte[] worksheetImage = null;
		topic = topicRepo.findById(topicId).get();
		if(topic != null){
			worksheetImage = topic.getWorkSheetImage();
		}else{
			throw new Exception();
		}
		return worksheetImage;
	}

	@Override
	public ResultVO updateWorkSheetForTopic(Integer topicId, MultipartFile mediaFile, String url) throws Exception {
		ResultVO result = new ResultVO();
		// Normalize file name
				if (mediaFile != null) {
					String audioName = StringUtils.cleanPath(mediaFile.getOriginalFilename());
					if (audioName.contains("..")) {
						throw new Exception("Sorry! Filename contains invalid path sequence ");
					}

				}
				try {
					if (topicRepo.findById(topicId).isPresent() && (mediaFile != null)) {
						Topic topic = topicRepo.findById(topicId).get();
						if (topic != null) {
							topic.setWorksheetLink(url);
							topic.setWorkSheetImage(mediaFile.getBytes());
							topicRepo.save(topic);
							result.setStatus(200);
							result.setMessage("Worksheet Updated Sucessfully...");
						}

					}
				} catch (Exception ex) {
					result.setStatus(400);
					result.setMessage("Unbale to update worksheet...");
					log.error("Could not store file, Please try again!", ex);
				}

		return result;
	}


}
