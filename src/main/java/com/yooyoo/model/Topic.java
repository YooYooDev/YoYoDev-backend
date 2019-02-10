package com.yooyoo.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
@Table(name = "topic")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Topic {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="topic_id_seq")
    @SequenceGenerator(name="topic_id_seq", sequenceName="topic_id_seq", allocationSize=1)
	private Integer id;
	
	private String name;
	
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="updated_date")
	private Date updatedDate;

	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="topic_id")
	private Set<Video> videoLinks;
	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="topic_id")
	private Set<Quiz> quizLinks;
	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="topic_id")
	private Set<Worksheet> worksheetLinks;
	
	@ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "topic_subject",
        joinColumns = @JoinColumn(name = "topic_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id"))
    private Set<Subject> subjects;
	
	
	@ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "topic_catagory",
        joinColumns = @JoinColumn(name = "topic_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private Set<Category> categories;
	
	
	
}
