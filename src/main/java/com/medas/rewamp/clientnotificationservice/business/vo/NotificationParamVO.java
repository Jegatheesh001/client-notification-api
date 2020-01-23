package com.medas.rewamp.clientnotificationservice.business.vo;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * VO to push notification to cloud
 * 
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Jan 9, 2020
 *
 */
@Data
@NoArgsConstructor
public class NotificationParamVO {
	/**
	 * Notification reference type (to distinguish notification of multiple module). eg app/reg
	 */
	private String referType;
	/**
	 * Reference Id for Reference type
	 */
	private Integer referId;
	/**
	 * Type of Notification eg. sms/email
	 */
	private String notificationType;
	/**
	 * Notification Id eg. Mobile No/ E-mail id
	 */
	private String notificationId;
	/**
	 * Notification Message
	 */
	private String notificationTemplate;
	/**
	 * Notification Subject
	 */
	private String notificationSubject;
	/**
	 * Instant Message
	 */
	private String instant;
	private LocalDateTime notificationTime;
	private LocalDateTime currentTime;
	private String clientId;
	private Integer branchId;
	
	private List<NotificationAttachmentVO> attachments;
	
	/**
	 * For Sending SMS Notifications
	 * 
	 * @param referType
	 * @param referId
	 * @param notificationType
	 * @param notificationId
	 * @param notificationTemplate
	 * @param instant
	 * @param notificationTime
	 * @param currentTime
	 * @param clientId
	 * @param branchId
	 */
	public NotificationParamVO(String referType, Integer referId, String notificationType, String notificationId,
			String notificationTemplate, String instant, LocalDateTime notificationTime, LocalDateTime currentTime,
			String clientId, Integer branchId) {
		super();
		this.referType = referType;
		this.referId = referId;
		this.notificationType = notificationType;
		this.notificationId = notificationId;
		this.notificationTemplate = notificationTemplate;
		this.instant = instant;
		this.notificationTime = notificationTime;
		this.currentTime = currentTime;
		this.clientId = clientId;
		this.branchId = branchId;
	}
	
	/**
	 * For Sending mail
	 * 
	 * @param referType
	 * @param referId
	 * @param notificationType
	 * @param notificationId
	 * @param notificationTemplate
	 * @param instant
	 * @param notificationTime
	 * @param currentTime
	 * @param clientId
	 * @param branchId
	 */
	public NotificationParamVO(String referType, Integer referId, String notificationType, String notificationId,
			String notificationTemplate, String notificationSubject, String instant, LocalDateTime notificationTime, LocalDateTime currentTime,
			String clientId, Integer branchId, List<NotificationAttachmentVO> attachments) {
		super();
		this.referType = referType;
		this.referId = referId;
		this.notificationType = notificationType;
		this.notificationId = notificationId;
		this.notificationTemplate = notificationTemplate;
		this.notificationSubject = notificationSubject;
		this.instant = instant;
		this.notificationTime = notificationTime;
		this.currentTime = currentTime;
		this.clientId = clientId;
		this.branchId = branchId;
		this.attachments = attachments;
	}
}
