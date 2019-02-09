package com.yooyoo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yooyoo.model.Video.VideoBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "topic_quiz")
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="quiz_id_seq")
    @SequenceGenerator(name="quiz_id_seq", sequenceName="quiz_id_seq", allocationSize=1)
	private Integer id;
	
	@Column(name="quiz_link")
	private String quizLink;
	
	@ManyToOne
	private Topic topic;
}
