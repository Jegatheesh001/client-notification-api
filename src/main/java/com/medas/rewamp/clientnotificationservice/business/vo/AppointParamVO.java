package com.medas.rewamp.clientnotificationservice.business.vo;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Jan 9, 2020
 *
 */
@Data
@ApiModel(description = "For Appointment request parameters")
public class AppointParamVO {
	@ApiModelProperty(notes = "Appointment Id")
	private Integer appointId;
	@ApiModelProperty(notes = "Old Appointment Id (for reschedule case)")
	private Integer oldAppointId;
	@ApiModelProperty(notes = "Appointment Template")
	private String appointTemplate;
	@ApiModelProperty(notes = "Appointment Time")
	private LocalDateTime appointTime;
	@ApiModelProperty(notes = "Mobile Number")
	private String mobileNo;
	@ApiModelProperty(notes = "Office id")
	private Integer officeId;
	@ApiModelProperty(notes = "Appointment Templates. (eg.Notification Templates)")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<TemplateVO> templates;
}
