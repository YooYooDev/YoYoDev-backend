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
public class AttendanceDTO {
	private Integer schoolId;
	private Integer grade;
	private String date;
	private List<StudentDTO> studentList;
	@Override
	public String toString() {
		return "AttendanceDTO [schoolId=" + schoolId + ", grade=" + grade + ", date=" + date + ", studentList="
				+ studentList + "]";
	}
	
	
	
}
