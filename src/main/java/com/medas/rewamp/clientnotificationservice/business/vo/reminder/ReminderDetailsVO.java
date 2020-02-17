package com.medas.rewamp.clientnotificationservice.business.vo.reminder;

import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 *
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Feb 17, 2020
 *
 */
@Data
@ApiModel(description = "Reminder Details VO")
public class ReminderDetailsVO {
	private String remarks;
	private LocalDate followupDate;
	private LocalDateTime createdDate;
	
	/**
	 * For query getReminderDetails in ReminderDaoImpl
	 * 
	 * @param remarks
	 * @param followupDate
	 * @param createdDate
	 */
	public ReminderDetailsVO(String remarks, LocalDate followupDate, LocalDateTime createdDate) {
		super();
		this.remarks = remarks;
		this.followupDate = followupDate;
		this.createdDate = createdDate;
	}
	
}
