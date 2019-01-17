package com.yooyoo.vo;

import java.util.List;

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
	private Integer studentId;
	private Integer schoolId;
	private Integer grade;
	private String date;
	private String studentName;
	private int status;
    private List<StudentDTO> studentList;

}
