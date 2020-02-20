package com.medas.rewamp.clientnotificationservice.business.vo.reminder;

import java.time.LocalDate;

import lombok.Data;

/**
 * For reminder search
 * 
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Feb 17, 2020
 *
 */
@Data
public class ReminderSearchVO {
	private String reminderType;
	private String subject;
	private Integer createdBy;
	private Integer followupBy;
	private Integer listFor;
	private String followDate;
	private LocalDate followupDate;
	private String closedStatus;
}
