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
public class SchoolReportVO {
	List<AttendanceReportVO> attendaneReport;
	List<FeeReportVO>  feesReport;
	List<AssignmentReportVO> assignmentReport;
	

}
