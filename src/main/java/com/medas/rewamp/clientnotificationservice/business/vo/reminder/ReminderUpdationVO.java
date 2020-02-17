package com.medas.rewamp.clientnotificationservice.business.vo.reminder;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 *
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Feb 17, 2020
 *
 */
@Data
@ApiModel(description = "Reminder Creation")
public class ReminderUpdationVO {
	@NotNull(message = "Reminder Id should not be blank")
	private Integer reminderId;
	private LocalDate followupDate;
	private String remarks;
}
