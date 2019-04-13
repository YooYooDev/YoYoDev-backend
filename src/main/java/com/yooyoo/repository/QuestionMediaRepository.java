package com.yooyoo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.yooyoo.model.QuestionMedia;

public interface QuestionMediaRepository extends CrudRepository<QuestionMedia, Integer> {

	public static String FIND_BY_QUESTION_ID = "select q from QuestionMedia q where q.questionId = :questionId";

	@Query(FIND_BY_QUESTION_ID)
	QuestionMedia findByQuestionId(@Param("questionId") Integer questionId);

}
