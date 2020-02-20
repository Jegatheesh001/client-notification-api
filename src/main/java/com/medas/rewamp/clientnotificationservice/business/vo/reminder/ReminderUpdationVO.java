package com.medas.rewamp.clientnotificationservice.business.vo.reminder;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * For Reminder Updation
 *
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Feb 17, 2020
 *
 */
@Data
@ApiModel(description = "Reminder Updation")
public class ReminderUpdationVO {
	@NotNull(message = "Reminder Id should not be blank")
	private Integer reminderId;
	private LocalDate followupDate;
	private Integer followupBy;
	private String remarks;
	private Integer createdBy;
}
