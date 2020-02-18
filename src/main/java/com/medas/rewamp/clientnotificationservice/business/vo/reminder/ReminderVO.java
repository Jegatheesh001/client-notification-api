package com.medas.rewamp.clientnotificationservice.business.vo.reminder;

import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * For Reminder List
 *
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Feb 17, 2020
 *
 */
@Data
@ApiModel(description = "Reminder Master VO")
public class ReminderVO {
	private String reminderType;
	private Integer reminderReferId;
	private Integer createdBy;
	private Integer followupBy;
	private LocalDate followupDate;
	private String subject;
	private String contact;
	private String closedStatus;
	
	/**
	 * For query getAllReminders in ReminderDaoImpl
	 * 
	 * @param reminderType
	 * @param reminderReferId
	 * @param createdBy
	 * @param followupBy
	 * @param followupDate
	 * @param subject
	 * @param contact
	 * @param closedStatus
	 */
	public ReminderVO(String reminderType, Integer reminderReferId, Integer createdBy, Integer followupBy,
			LocalDate followupDate, String subject, String contact, String closedStatus) {
		super();
		this.reminderType = reminderType;
		this.reminderReferId = reminderReferId;
		this.createdBy = createdBy;
		this.followupBy = followupBy;
		this.followupDate = followupDate;
		this.subject = subject;
		this.contact = contact;
		this.closedStatus = closedStatus;
	}
}
