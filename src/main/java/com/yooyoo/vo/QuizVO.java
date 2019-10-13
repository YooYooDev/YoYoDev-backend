package com.yooyoo.vo;

import java.util.List;

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
public class QuizVO {
	private Integer id;
	private Integer topicId;
	private String quizName;
	private String topicName;
	private List<QuestionVO> questions;
	

}
