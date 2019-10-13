package com.yooyoo.service;

import org.springframework.web.multipart.MultipartFile;

import com.yooyoo.model.QuestionMedia;

public interface QuestionMediaService {

	void saveQuestionMedia(Integer questionId, MultipartFile media) throws Exception;

	QuestionMedia getQuestionMedia(Integer questionId) throws Exception;

}
