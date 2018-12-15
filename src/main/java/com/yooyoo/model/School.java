package com.yooyoo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "school")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class School {

	@Id
	@GeneratedValue
	int id;
	String name;
	int code;
	String owner_name;
	String address1;
	String post;
	String pin;
	String state;
	String country;
	String contact_person;
	String owner_mobile;
	String email_id;
	int active;
	String registration_name;
	int enable_fees;
	int enable_attendance;
	int enable_printed_worksheet;
	Date created_at;
	Date updatedd_at;
	String deleted;

}
