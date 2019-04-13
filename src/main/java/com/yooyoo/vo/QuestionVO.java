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
public class QuestionVO {
	private Integer id;
	private String question;
	private String answer;
	private String mediaLink;
	private String contentType;
	private String option1;
	private String option2;

}
