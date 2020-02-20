package com.medas.rewamp.clientnotificationservice.business.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderCreationVO;
import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderUpdationVO;

import lombok.Data;

/**
 * reminder details table
 *
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Feb 17, 2020
 *
 */
@Data
@Entity
@Table(name="reminder_details")
public class ReminderDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reminderDetailsId;
	@ManyToOne
	@JoinColumn(name = "reminder_id", nullable = false, foreignKey =  @ForeignKey(name = "rmdr"))
	private ReminderHeader reminder;
	@Column(nullable = false, length = 500)
	private String remarks;
	@Column(nullable = false)
	private LocalDate followupDate;
	@Column(nullable = false)
	private LocalDateTime createdDate;
	@Column(nullable = false)
	private Integer createdBy;
	
	/**
	 * Copying ReminderCreationVO values
	 * 
	 * @param reminderVO
	 */
	public ReminderDetails(ReminderCreationVO reminderVO) {
		this.remarks = reminderVO.getRemarks();
		this.followupDate = reminderVO.getFollowupDate();
		this.createdBy = reminderVO.getCreatedBy();
		this.createdDate = LocalDateTime.now();
	}

	/**
	 * Copying ReminderUpdationVO values
	 * 
	 * @param reminderVO
	 */
	public ReminderDetails(ReminderUpdationVO reminderVO) {
		this.reminder = new ReminderHeader(reminderVO.getReminderId());
		this.remarks = reminderVO.getRemarks();
		this.followupDate = reminderVO.getFollowupDate();
		this.createdDate = LocalDateTime.now();
		this.createdBy = reminderVO.getCreatedBy();
	}
}
