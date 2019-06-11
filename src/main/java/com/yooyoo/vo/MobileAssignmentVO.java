package com.yooyoo.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@JsonInclude(content=Include.NON_NULL)
public class MobileAssignmentVO {

	private String description;
	private Integer assignmentId;
	private Integer topicId;
	private String workSheetLink;
	private String workSheetMedia;
	private List<VideoVO> videos;
	private List<QuestionVO> questions;
	private String date;

}
