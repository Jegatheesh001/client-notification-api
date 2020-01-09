package com.medas.rewamp.clientnotificationservice.business.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Jan 9, 2020
 *
 */
@Data
@AllArgsConstructor
public class NotificationParamVO {
	private String referType;
	private Integer referId;
	private String notificationType;
	private String notificationId;
	private String notificationTemplate;
	private LocalDateTime notificationTime;
	private LocalDateTime currentTime;
	private String clientId;
	private Integer branchId;
}
