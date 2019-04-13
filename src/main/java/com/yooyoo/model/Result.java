package com.yooyoo.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "result")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Result {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "result_id_seq")
	@SequenceGenerator(name = "result_id_seq", sequenceName = "result_id_seq", allocationSize = 1)
	private Integer id;
	private Integer studentId;
	private Integer questionId;
	private Boolean result;
	private Date date;
	

}
