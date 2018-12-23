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
public class AttendanceVO {
	private Integer id;
	private StudentVO student;
	private SchoolInfo school;
	private GradeVO grade;
	private Date attend_date;
	private Integer status;
	private Date created_at;
	private Date updatedd_at;
	private String deleted;

}
