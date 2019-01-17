package com.yooyoo.model;


import java.io.Serializable;
import javax.persistence.Column;
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
@Table(name = "cred_manager")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CredManager implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cred_manager_id_seq")
	@SequenceGenerator(name = "cred_manager_id_seq", sequenceName = "cred_manager_id_seq", allocationSize = 1)
	int id;
	@Column(name = "user_name")
	String user_name;
	@Column(name = "email")
	String email;
	@Column(name = "password")
	String password;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_id")
	School school;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	Role role;
	@Column(name = "fullname")
	String fullName;
	String mobile;
	String city;
	String active;
}
