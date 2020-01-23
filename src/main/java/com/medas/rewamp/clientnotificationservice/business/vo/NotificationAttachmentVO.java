package com.medas.rewamp.clientnotificationservice.business.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Mail Attachments
 * 
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Jan 23, 2020
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationAttachmentVO {
	private String attachmentType;
	private String attachmentName;
	private String attachment;
	private String fileExtension;
}
