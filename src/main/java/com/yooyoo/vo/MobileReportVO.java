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
public class MobileReportVO {
	private Integer totalNoOfAssignment;
	private Integer noOfQuestionFaced;
	private Integer noOfVideosWatched;
	private Integer noOfWorksheetAppeared;

}
