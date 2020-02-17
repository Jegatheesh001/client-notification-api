package com.medas.rewamp.clientnotificationservice.business.vo.reminder;

import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * For Reminder Creation
 *
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Feb 17, 2020
 *
 */
@Data
@ApiModel(description = "Reminder Creation")
public class ReminderCreationVO {
	private String reminderType;
	private Integer reminderReferId;
	private Integer createdBy;
	private Integer followupBy;
	private LocalDate followupDate;
	private String subject;
	private String contact;
	private String remarks;
}
