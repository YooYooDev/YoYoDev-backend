package com.yooyoo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "question_media")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class QuestionMedia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "questionmedia_id_seq")
	@SequenceGenerator(name = "questionmedia_id_seq", sequenceName = "questionmedia_id_seq", allocationSize = 1)
	private Integer id;
	private Integer questionId;
	private String contentType;
	@Lob
	@Column(length=100000)
	private byte[] media;
	

}
