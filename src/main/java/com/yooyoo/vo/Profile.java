package com.yooyoo.vo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yooyoo.model.School;

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
public class Profile implements Serializable {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private String accessToken;
	@XmlElement
	private String role;
	@XmlElement
	@JsonProperty("userInfo")
	private UserInfo userInfo;

	@XmlElement
	@JsonProperty("school")
	private School school;

	@XmlElement
	@JsonProperty("student")
	private StudentVO student;

	private String message;
	
	private Integer status;

}
