package com.yooyoo.vo;

import java.util.Date;

import com.yooyoo.vo.AttendanceDTO.AttendanceDTOBuilder;
import com.yooyoo.vo.StudentVO.StudentVOBuilder;

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
public class StudentDTO {

	private Integer studentId;
	private boolean attendanceStatus;
	
}
