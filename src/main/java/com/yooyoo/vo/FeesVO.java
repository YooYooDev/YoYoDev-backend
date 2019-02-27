package com.yooyoo.vo;

import java.util.Date;

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
public class FeesVO {
	private Integer id;
	private String studentName;
	private int studentId ;
	private Float totalBillAmount;
	private Float paidBillAmount;
	private Float pendingAmount;
	private int schoolId;
	private Date created_at;
	private Date updatedd_at;
	private int deleted ;
	private String gradeName ;
	private byte[] image1;
	

}
