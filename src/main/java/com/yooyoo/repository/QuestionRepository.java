package com.yooyoo.repository;

import org.springframework.data.repository.CrudRepository;

import com.yooyoo.model.Question;

public interface QuestionRepository extends CrudRepository<Question, Integer>{

}
