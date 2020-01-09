package com.medas.rewamp.clientnotificationservice.business.vo;

import java.time.LocalDateTime;

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
}
