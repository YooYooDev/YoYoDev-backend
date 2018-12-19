package com.yooyoo.vo;

import java.io.Serializable;

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
public class ResultVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String messge;
	private int status;
	
}
