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
@Table(name = "topic_worksheet")
@Getter
@Setter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Worksheet {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="worksheet_id_seq")
    @SequenceGenerator(name="worksheet_id_seq", sequenceName="worksheet_id_seq", allocationSize=1)
	private Integer id;
	
	@Column(name="worksheet_link")
	private String worksheetLink;
	
	@ManyToOne
	private Topic topic;
	
}
