package com.yooyoo.vo;

import java.util.Set;

import com.yooyoo.model.Student;
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
public class GradeVO {
	int id;
	String name;
	Set<Student> students;

}
