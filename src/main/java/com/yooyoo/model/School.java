package com.yooyoo.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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
@Table(name = "school")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class School implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "school_id_seq")
	@SequenceGenerator(name = "school_id_seq", sequenceName = "school_id_seq", allocationSize = 1)
	Integer id;

	@Column(name="name")
	String name;
	
	@Column(name="code")
	int code;
	
	@Column(name="owner_name")
	String ownerName;

	@Column(name="address1")
	String address;

	@Column(name="post")
	String post;
	
	@Column(name="pin")
	String pin;
	
	@Column(name="state")
	String state;
	
	@Column(name="country")
	String country;
	
	@Column(name="contact_person")
	String contactPerson;
	
	@Column(name="owner_mobile")
	String ownerMobile;
	
	@Column(name="email_id")
	String emailId;
	
	@Column(name="active")
	int active;
	
	@Column(name="registration_name")
	String registrationName;
	
	@Column(name="enable_fees")
	int enableFees;
	
	@Column(name="enable_attendance")
	int enableAttendance;
	
	@Column(name="enable_printed_worksheet")
	int enablePrintedWorksheet;
	
	@Column(name="created_at")
	Date createdAt;
	
	@Column(name="updatedd_at")
	Date updatedAt;
	
	@Column(name="deleted")
	String deleted;
	
	@OneToMany
	Set<Student> students;
	
	@PrePersist
	@PreUpdate
	public void auditTrail() {
		final Date now = Calendar.getInstance().getTime();
		if (createdAt == null) {
			setCreatedAt(now);
		}
		setUpdatedAt(now);
	}
	
	/**
	 * @param createdAt
	 *            the createdAt to set
	 */
	public void setCreatedAt(final Date createdAt) {
		this.createdAt = (createdAt == null) ? Calendar.getInstance().getTime() : new Date(createdAt.getTime());
	}

	/**
	 * @return the updatedAt
	 */
	/**
	 * @param updatedAt
	 *            the updatedAt to set
	 */
	public void setUpdatedAt(final Date updatedAt) {
		this.updatedAt = (updatedAt == null) ? Calendar.getInstance().getTime() : new Date(updatedAt.getTime());
	}


}
