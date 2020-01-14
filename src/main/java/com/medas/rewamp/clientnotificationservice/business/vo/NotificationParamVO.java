package com.medas.rewamp.clientnotificationservice.business.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
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
	 * Instant Message
	 */
	private String instant;
	private LocalDateTime notificationTime;
	private LocalDateTime currentTime;
	private String clientId;
	private Integer branchId;
	
}
