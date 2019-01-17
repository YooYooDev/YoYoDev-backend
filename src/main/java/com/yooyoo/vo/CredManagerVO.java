package com.yooyoo.vo;

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
public class CredManagerVO {

	int id;
	String userName;
	String email;
	String password;
	int  schoolId;
	int roleId;
	String role;
	String fullName;
	String mobile;
	String city;
	String schoolName;

}
