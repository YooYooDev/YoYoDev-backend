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
	private StudentVO student ;
	private Float totalBillAmount;
	private Float paidBillAmount;
	private Float pendingAmount;
	private SchoolInfo school;
	private Date created_at;
	private Date updatedd_at;
	private String deleted ;

}
