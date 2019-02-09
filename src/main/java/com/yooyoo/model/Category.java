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
import com.yooyoo.model.Subject.SubjectBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category_master")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="category_id_seq")
    @SequenceGenerator(name="category_id_seq", sequenceName="category_id_seq", allocationSize=1)
	private Integer id;
	
	@Column(name="category_name")
	String name ;
	
	@Column(name="created_date")
	Date createdDate;

	@Column(name="updated_date")
	Date updatedDate;
	
	@ManyToMany(mappedBy = "categories")
    private Set<Topic> topics = new HashSet<>();
}
