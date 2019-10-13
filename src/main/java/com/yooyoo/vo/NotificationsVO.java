package com.yooyoo.vo;

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
public class NotificationsVO {
	private Integer id;
	private String header;
	private String message;
	private int  schoolId;
	private int studentId;
	private String studentName;
	private String gradeName;
	private String schoolName;
	private int gradeId;
	private Date created_at;
	private Date updatedd_at;
	private String deleted;

}
