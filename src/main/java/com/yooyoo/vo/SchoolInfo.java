package com.yooyoo.vo;

import javax.xml.bind.annotation.XmlElement;
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
public class SchoolInfo {
	@XmlElement
	String name;
	@XmlElement
	int code;
	@XmlElement
	String owner_name;
	@XmlElement
	String address1;
	@XmlElement
	String post;
	@XmlElement
	String pin;
	@XmlElement
	String state;
	@XmlElement
	String country;
	@XmlElement
	String contact_person;
	@XmlElement
	String owner_mobile;
	@XmlElement
	String email_id;
	@XmlElement
	int active;
	@XmlElement
	String registration_name;
	@XmlElement
	int enable_fees;
	@XmlElement
	int enable_attendance;
	@XmlElement
	int enable_printed_worksheet;

}
