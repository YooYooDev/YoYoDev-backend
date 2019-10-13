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
public class FeeReportVO {
	private String gradeName;
	private long totalTutionFee;
	private long totalTransportFee;
	private long totalTutionFeepaid;
	private long totalTransportFeePaid;

}
