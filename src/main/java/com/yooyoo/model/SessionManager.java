package com.yooyoo.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "session_manager")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SessionManager {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="session_id_seq")
    @SequenceGenerator(name="session_id_seq", sequenceName="session_id_seq", allocationSize=1)
	private Integer id;
	@Column(name="user_id")
	private Integer userId;
	@Column(name="source")
	private String source;
	@Column(name="session_id")
	private String  sessionId;
	@Column(name="created_at")
	private Date createdAt;
	@Column(name="updatedd_at")
	private Date updatedAt;
	
	@PrePersist
	@PreUpdate
	public void auditTrail() {
		final Date now = Calendar.getInstance().getTime();
		if (createdAt == null) {
			setCreatedAt(now);
		}
		setUpdatedAt(now);
	}

}
