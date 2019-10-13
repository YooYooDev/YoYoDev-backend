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
	private String schoolName;
	private String schoolCode;
	private String ownerName;
	private String studentStrength;
	private Integer noOfTopicsAssigned;	
	private Integer noOfTopicViewed;	
	private Integer noOfQuestionFaced;
	private Integer noOfCorrectAnswers;
	private Integer noOfVideosWatched;
	private Integer noOfWorksheetAppeared;
	private Integer attendanceTakenDays;
	private Integer presentDays;
	private Long totalTutionFee;
	private Long totalTransportFee;
	private Long totalTutionFeepaid;
	private Long totalTransportFeePaid;
}
