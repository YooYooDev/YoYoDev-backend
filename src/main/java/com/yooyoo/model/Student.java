package com.yooyoo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="students_id_seq")
    @SequenceGenerator(name="students_id_seq", sequenceName="students_id_seq", allocationSize=1)
	int id ;
	String first_name;
	String middle_name;
	String last_name ;
	String role;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_id")
	School school;
	String p_email;
	String s_email;
	String parent_mobile1;
	String parent_mobile2;
	String password;
	Date created_at;
	Date updatedd_at;
	String deleted;

}
