package com.yooyoo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.yooyoo.model.Topic;

public interface TopicRepository extends CrudRepository<Topic, Integer>{
	
	public static String GET_TOPIC_BY_NAME = "select t from Topic t where t.name = :name";
	
	public static String GET_ALL_TOPICS = "select distinct s from Topic s";
	
	public static String GET_ALL_TOPICS_BY_SUBJECT_ID = "select distinct s from Topic s where s.subjects.id = :id";
	
	
	@Query(GET_TOPIC_BY_NAME)
	public Topic getTopicByName(@Param("name") String name);
	
	@Query(GET_ALL_TOPICS)
	public List<Topic>  getAllTopics();
	
	@Query(GET_ALL_TOPICS_BY_SUBJECT_ID)
	public List<Topic>  getAllTopicsBySubjectId(@Param("id") Integer name); 


}
