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
public class AssignmentReportVO {
	private String gradeName;
	private int noOfVideosViewed;
	private int noOfWorkSheetAppeared;
	private int noOfQuestionsAppeared;
	private int noOfCorrectAnswers;

}
