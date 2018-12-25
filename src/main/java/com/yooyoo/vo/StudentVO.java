package com.yooyoo.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class StudentVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id ;
	String firstName;
	String middleName;
	String lastName ;
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
	String deleted;
	
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

}
