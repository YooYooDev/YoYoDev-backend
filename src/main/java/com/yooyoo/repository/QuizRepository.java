package com.yooyoo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.yooyoo.model.Quiz;

public interface QuizRepository extends CrudRepository<Quiz, Integer>{
	
	public static String GET_QUIZ_BY_TOPIC_ID = "select q from Quiz q where q.topic_id = :topic_id";
	
	@Query(GET_QUIZ_BY_TOPIC_ID)
	public List<Quiz> getQuizByTopicId(@Param("topic_id") Integer topic_id);

}
