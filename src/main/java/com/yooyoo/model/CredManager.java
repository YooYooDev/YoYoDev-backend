package com.yooyoo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
public class CredManager {
	@Id
	@GeneratedValue
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
}
