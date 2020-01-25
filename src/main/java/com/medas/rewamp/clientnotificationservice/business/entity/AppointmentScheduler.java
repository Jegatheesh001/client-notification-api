package com.medas.rewamp.clientnotificationservice.business.entity;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 *
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Jan 14, 2020
 *
 */
@Data
@Entity
@Table(name="appointment_scheduler")
public class AppointmentScheduler {
	@Id
	@GeneratedValue
	private Integer schedulerId;
	@Column(nullable = false)
	private LocalTime runAt;
	@Column(nullable = false)
	private Integer prior;
	@Column(nullable = false)
	private String template;
}
