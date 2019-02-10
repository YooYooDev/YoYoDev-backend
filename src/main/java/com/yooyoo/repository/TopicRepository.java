package com.yooyoo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.yooyoo.model.Topic;

public interface TopicRepository extends CrudRepository<Topic, Integer>{
	
	public static String GET_TOPIC_BY_NAME = "select t from Topic t where t.name = :name";
	
	
	@Query(GET_TOPIC_BY_NAME)
	public Topic getTopicByName(@Param("name") String name);


}
