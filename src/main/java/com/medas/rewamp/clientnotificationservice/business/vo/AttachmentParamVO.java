package com.medas.rewamp.clientnotificationservice.business.vo;

import lombok.Data;

/**
 * Mail Attachments Parameters
 * 
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Jan 23, 2020
 *
 */
@Data
public class AttachmentParamVO {
	private String attachmentType;
	private String attachmentName;
	private String attachmentPath;
}
