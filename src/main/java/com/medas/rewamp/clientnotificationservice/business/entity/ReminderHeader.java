package com.medas.rewamp.clientnotificationservice.business.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderCreationVO;

import lombok.Data;

/**
 * Master Entry for reminders
 * 
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Feb 17, 2020
 *
 */
@Data
@Entity
@Table(name="reminder_header")
public class ReminderHeader {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reminderId;
	/**
	 * lead, complaint, service
	 */
	@Column(nullable = false, length = 45)
	private String reminderType;
	/**
	 * refer id for reminderType
	 */
	@Column(nullable = false)
	private Integer reminderReferId;
	@Column(nullable = false)
	private Integer createdBy;
	@Column(nullable = false)
	private Integer followupBy;
	@Column(nullable = false, length = 50)
	private String subject;
	@Column(nullable = false)
	private LocalDate followupDate;
	@Column(nullable = false, length = 50)
	private String contact;
	@Column(nullable = false, length = 1)
	private String closedStatus;
	@Column(nullable = false)
	private LocalDateTime lastUpdated;
	
	/**
	 * CReate object with primary key
	 * 
	 * @param reminderId
	 */
	public ReminderHeader(Integer reminderId) {
		this.reminderId = reminderId;
	}
	
	/**
	 * Copying ReminderCreationVO values
	 * 
	 * @param reminderVO
	 */
	public ReminderHeader(ReminderCreationVO reminderVO) {
		this.reminderType = reminderVO.getReminderType();
		this.reminderReferId = reminderVO.getReminderReferId();
		this.createdBy = reminderVO.getCreatedBy();
		this.followupBy = reminderVO.getFollowupBy();
		this.subject = reminderVO.getSubject();
		this.followupDate = reminderVO.getFollowupDate();
		this.contact = reminderVO.getContact();
		this.closedStatus = "N";
		this.lastUpdated = LocalDateTime.now();
	}
}
