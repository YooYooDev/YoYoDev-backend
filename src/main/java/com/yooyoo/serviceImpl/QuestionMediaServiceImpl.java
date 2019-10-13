package com.yooyoo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.yooyoo.model.QuestionMedia;
import com.yooyoo.repository.QuestionMediaRepository;
import com.yooyoo.repository.QuestionRepository;
import com.yooyoo.service.QuestionMediaService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuestionMediaServiceImpl implements QuestionMediaService {

	@Autowired
	QuestionMediaRepository questionMediaRepo;
	
	@Autowired
	QuestionRepository questionRepo;

	@Override
	public QuestionMedia getQuestionMedia(Integer questionId) throws Exception {
		QuestionMedia media = null;
		QuestionMedia qmedia = null;
		media = questionMediaRepo.findByQuestionId(questionId);
		if(media != null){
			qmedia = media;
		}else{
			throw new Exception();
		}
		return qmedia;
	}

	@Override
	public void saveQuestionMedia(Integer questionId,  MultipartFile mediaFile) throws Exception {
		QuestionMedia media = new QuestionMedia();
		// Normalize file name
		if(mediaFile != null){
			String audioName = StringUtils.cleanPath(mediaFile.getOriginalFilename());
			if (audioName.contains("..")) {
				throw new Exception("Sorry! Filename contains invalid path sequence ");
			}
			
		}
		
		try {
			if (questionRepo.findById(questionId).isPresent() && (mediaFile != null)) {
				QuestionMedia medias = questionMediaRepo.findByQuestionId(questionId);
				if(medias == null){
					media.setMedia(mediaFile.getBytes());
					media.setContentType(mediaFile.getContentType());
					media.setQuestionId(questionId);
					questionMediaRepo.save(media);
				}else{
					medias.setMedia(mediaFile.getBytes());
					medias.setContentType(mediaFile.getContentType());
					questionMediaRepo.save(media);
				}
				
			}
		} catch (Exception ex) {
			log.error("Could not store file, Please try again!", ex);
		}

	}

}
