package com.yooyoo.service;

import java.util.List;

import com.yooyoo.model.Result;
import com.yooyoo.vo.QuizVO;
import com.yooyoo.vo.ResultVO;

public interface QuizService {
	
	ResultVO saveQuiz(QuizVO quiz);
	
	List<QuizVO> getAllQuiz();
	
	List<QuizVO> getQuizByTopic(Integer TopicId);
	
	ResultVO saveResult(List<Result> results);

	ResultVO deleteQuiz(Integer id);

	ResultVO deleteResults();
	
	

}
