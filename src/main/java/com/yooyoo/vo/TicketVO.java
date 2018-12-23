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
public class TicketVO {
	private Integer id;
	private String subject;
	private String message;
	private Boolean resolution;
	private SchoolInfo schoolId;
	private Date created_at;
	private Date updatedd_at;
	private String deleted ;
}
