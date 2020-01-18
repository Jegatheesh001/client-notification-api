package com.medas.rewamp.clientnotificationservice.business.entity;

import java.time.LocalTime;

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
	private LocalTime runAt;
	private Integer prior;
	private String template;
}
