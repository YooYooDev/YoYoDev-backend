package com.yooyoo.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class StudentVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id;
	String firstName;
	String middleName;
	String lastName;
	String role;
	int schoolId;
	int gradeId;
	String primaryEmail;
	String secondaryEmail;
	String parentMobile1;
	String parentMobile2;
	String password;
	Date createdAt;
	Date updatedAt;
	int deleted;

	String dob;
	String photo;
	String gender;
	String fatherName;
	String motherName;
	String fatherProfession;
	String motherProfession;
	String address;
	String city;
	String state;
	String pinCode;
	String gradeName;

}
