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
public class ReportVO {
	private int id;
	private Integer noOfQuestionFaced;
	private Integer noOfCorrectAnswers;
	private Integer noOfVideosWatched;
	private Integer noOfWorksheetAppeared;
	private Integer attendanceTakenDays;
	private Integer presentDays;
	private float outstandingFees;
	private float totalFees;
	
	

}
