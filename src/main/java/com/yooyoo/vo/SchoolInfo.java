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
	long id;
	@XmlElement
	String name;
	@XmlElement
	int code;
	@XmlElement
	String ownerName;
	@XmlElement
	String address;
	@XmlElement
	String post;
	@XmlElement
	String pin;
	@XmlElement
	String state;
	@XmlElement
	String country;
	@XmlElement
	String contactPerson;
	@XmlElement
	String ownerMobile;
	@XmlElement
	String emailId;
	@XmlElement
	int active;
	@XmlElement
	String registrationName;
	@XmlElement
	int enableFees;
	@XmlElement
	int enableAttendance;
	@XmlElement
	int enablePrintedWorksheet;

}
