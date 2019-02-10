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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "topic_video")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Video {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="video_id_seq")
    @SequenceGenerator(name="video_id_seq", sequenceName="video_id_seq", allocationSize=1)
	private Integer id;
	
	@Column(name="video_link")
	private String videoLink;
	
	@ManyToOne
	private Topic topic;
	
	
}
