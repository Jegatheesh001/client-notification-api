package com.medas.rewamp.clientnotificationservice.business.vo;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 *
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Jan 9, 2020
 *
 */
@Data
public class AppointParamVO {
	private Integer appointId;
	private Integer oldAppointId;
	private String appointTemplate;
	private LocalDateTime appointTime;
	private String mobileNo;
	private Integer officeId;
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<TemplateVO> templates;
}
