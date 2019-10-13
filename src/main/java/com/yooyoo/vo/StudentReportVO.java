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
public class StudentReportVO {
	private int id;
	private String firstName;
	private String lastName;
	private String schoolName;
	private String gradeName;
	private int attendanceTakenDays;
	private int presentDays;
	private float tutionFee;
	private float transportationFee;
	private float pendingFee;
	private int noOfQuestionsFaced;
	private int correctAnswers;
	private int noOfVideoWatched;
	private int noOfWorkSheetAppeared;
	private long totalTutionFeePaid;
	private long totalTransportFeePaid;
	

}
