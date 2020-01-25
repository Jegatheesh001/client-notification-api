package com.medas.rewamp.clientnotificationservice.business.vo;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Mail Parameter VO
 *
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Jan 23, 2020
 *
 */
@Data
@ApiModel(description = "Mail request parameters")
public class MailParamVO {
	@NotNull(message = "E-mail Refer Id should not be blank")
	private Integer mailRequestId;
	@ApiModelProperty(notes = "E-mail Id")
	@NotNull(message = "E-mail Id should not be blank")
	@Pattern(regexp = "^(.+)@(.+)$", message = "Not a valid mail") 
	private String mailId;
	@NotNull(message = "E-mail Subject should not be blank")
	@ApiModelProperty(notes = "E-mail Subject")
	private String mailSubject;
	@NotNull(message = "E-mail Template should not be blank")
	@ApiModelProperty(notes = "E-mail Template")
	private String mailTemplate;
	@NotNull(message = "Office Id should not be blank")
	private Integer officeId;
	@ApiModelProperty(notes = "E-mail Attachments")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<AttachmentParamVO> attachments;
}
