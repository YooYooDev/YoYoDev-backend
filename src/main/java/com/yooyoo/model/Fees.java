package com.yooyoo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "fees")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Fees {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="fees_id_seq")
    @SequenceGenerator(name="fees_id_seq", sequenceName="fees_id_seq", allocationSize=1)
	private Integer id;
	
	@Column(name = "student_name")
	private String studentName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	Student student ;
	
	@Column(name = "total_bill_amount")
	Float totalBillAmount;
	
	@Column(name = "paid_bill_amount")
	Float paidBillAmount;
	
	@Column(name = "pending_amount")
	Float pendingAmount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_id")
	private School schoolId;
	
	Date created_at;
	Date updatedd_at;
	String deleted ;

}
