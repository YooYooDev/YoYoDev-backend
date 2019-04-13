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
public class VideoVO {
	
	private Integer AssignmentId;
	private String videoLink;
	private String videoThumbnail;
	private String videoSubtitle;

}
