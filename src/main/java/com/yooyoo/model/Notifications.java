package com.yooyoo.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Notifications {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notifications_id_seq")
	@SequenceGenerator(name = "notifications_id_seq", sequenceName = "notifications_id_seq", allocationSize = 1)
	private Integer id;
	private String header;
	private String message;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "school_id")
	private School school;
	
	@Column(name = "student_id")
	private Integer studentId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "grade_id")
	private Grade grade;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created_at;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedd_at;
	private String deleted;
	
	@PrePersist
	@PreUpdate
	public void auditTrail() {
		final Date now = Calendar.getInstance().getTime();
		if (created_at == null) {
			setCreatedAt(now);
		}
		setUpdatedAt(now);
	}
	
	/**
	 * @param createdAt
	 *            the createdAt to set
	 */
	public void setCreatedAt(final Date createdAt) {
		this.created_at = (createdAt == null) ? Calendar.getInstance().getTime() : new Date(createdAt.getTime());
	}

	/**
	 * @return the updatedAt
	 */
	/**
	 * @param updatedAt
	 *            the updatedAt to set
	 */
	public void setUpdatedAt(final Date updatedAt) {
		this.updatedd_at = (updatedAt == null) ? Calendar.getInstance().getTime() : new Date(updatedAt.getTime());
	}

}
