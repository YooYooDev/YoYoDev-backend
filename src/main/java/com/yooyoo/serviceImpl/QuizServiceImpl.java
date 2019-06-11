package com.yooyoo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.yooyoo.model.Question;
import com.yooyoo.model.Quiz;
import com.yooyoo.model.Result;
import com.yooyoo.model.Topic;
import com.yooyoo.repository.QuestionRepository;
import com.yooyoo.repository.QuizRepository;
import com.yooyoo.repository.ResultRepository;
import com.yooyoo.repository.TopicRepository;
import com.yooyoo.service.QuizService;
import com.yooyoo.util.VOMapper;
import com.yooyoo.vo.QuizVO;
import com.yooyoo.vo.ResultVO;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuizRepository quizRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private ResultRepository resultRepository;

	@Autowired
	private TopicRepository topicRepository;

	@Override
	public ResultVO saveQuiz(QuizVO quiz) {
		ResultVO result = new ResultVO();
		if (quiz != null) {
			try {
				Optional<Topic> topi = topicRepository.findById(quiz.getTopicId());
				if (topi.isPresent()) {
					Quiz quizModel = VOMapper.getQuizModel(quiz);
					List<Question> questions = quizModel.getQuestions();
					for (Question q : questions) {
						questionRepository.save(q);
					}
					quizRepository.save(quizModel);
					result.setStatus(200);
					result.setMessage("Quiz has been saved sucessfully");
				} else {
					result.setStatus(404);
					result.setMessage("Topic Not Found...");
				}
			} catch (DataIntegrityViolationException cve) {
				result.setStatus(400);
				result.setMessage("Duplicate Quiz or Duplicate Questions or Same topic already mapped to this quiz");
			} catch (Exception e) {
				e.printStackTrace();
				result.setStatus(400);
				result.setMessage("Invalid request");
			}
		} else {
			result.setStatus(400);
			result.setMessage("Invalid request");
		}
		return result;
	}

	@Override
	public List<QuizVO> getQuizByTopic(Integer topicId) {
		List<QuizVO> quizVOs = new ArrayList<>();
		List<Quiz> quizs = quizRepository.getQuizByTopicId(topicId);
		for (Quiz q : quizs) {
			Optional<Topic> topic = topicRepository.findById(q.getTopic_id());
			QuizVO vo = VOMapper.getQuizVO(q);
			vo.setTopicName(topic.get().getName());
			quizVOs.add(vo);
		}
		
		return quizVOs;
	}

	@Override
	public List<QuizVO> getAllQuiz() {
		Iterable<Quiz> quizs = quizRepository.findAll();
		List<QuizVO> quizVOs = new ArrayList<>();
		for (Quiz q : quizs) {
			Optional<Topic> topic = topicRepository.findById(q.getTopic_id());
			QuizVO vo = VOMapper.getQuizVO(q);
			vo.setTopicName(topic.get().getName());
			quizVOs.add(vo);
		}
		return quizVOs;
	}

	@Override
	public ResultVO saveResult(List<Result> results) {
		ResultVO result = new ResultVO();
		if (results != null && !results.isEmpty()) {
			for (Result sResult : results) {
				sResult.setAttempts(1);
				resultRepository.save(sResult);
			}

		} else {
			result.setStatus(400);
			result.setMessage("No results found to save...");
		}
		return result;
	}

}
