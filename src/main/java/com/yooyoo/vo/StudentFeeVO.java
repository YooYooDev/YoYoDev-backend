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
public class StudentFeeVO {
	private int id ;
	private int studentId;
	private String firstName;
	private String middleName;
	private String lastName ;
	private int schoolId;
	private int gradeId;
	private String gradeName;
	private Float tutionFee;
	private Float transportationFee;
	private Float paidTutionFee;
	private Float paidTransportFee;

}
