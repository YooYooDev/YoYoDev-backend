package com.yooyoo.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yooyoo.model.Topic.TopicBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "subject_master")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="subject_id_seq")
    @SequenceGenerator(name="subject_id_seq", sequenceName="subject_id_seq", allocationSize=1)
	private Integer id;
	
	@Column(name="subject_name")
	String name ;
	
	@Column(name="created_date")
	Date createdDate;

	@Column(name="updated_date")
	Date updatedDate;
	
	@ManyToMany(mappedBy = "subjects")
    private Set<Topic> topics = new HashSet<>();
}
