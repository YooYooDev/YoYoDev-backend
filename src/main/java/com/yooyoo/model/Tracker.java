package com.yooyoo.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tracker")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class Tracker {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "student_id")
	private Integer studentId;

	@Column(name = "assignment_id")
	private Integer assignmentId;
	
	private Integer schoolId;

	@Column(name = "subject")
	private String subject;

	@Column(name = "tracking_type")
	private String trackingType;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_date")
	private Date updatedDate;

	@PrePersist
	@PreUpdate
	public void auditTrail() {
		final Date now = Calendar.getInstance().getTime();
		if (createdDate == null) {
			setCreatedAt(now);
		}
		setUpdatedAt(now);
	}

	/**
	 * @param createdAt
	 *            the createdAt to set
	 */
	public void setCreatedAt(final Date createdAt) {
		this.createdDate = (createdAt == null) ? Calendar.getInstance().getTime() : new Date(createdAt.getTime());
	}

	/**
	 * @return the updatedAt
	 */
	/**
	 * @param updatedAt
	 *            the updatedAt to set
	 */
	public void setUpdatedAt(final Date updatedAt) {
		this.updatedDate = (updatedAt == null) ? Calendar.getInstance().getTime() : new Date(updatedAt.getTime());
	}

}
