package com.yooyoo.vimeo.pojo;

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
public class Vimeo {
	private String videoLlink;
	private String thumbLInk;
	private  String subTitle;

}
