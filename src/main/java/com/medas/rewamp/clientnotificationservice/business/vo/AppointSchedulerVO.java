package com.medas.rewamp.clientnotificationservice.business.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Jan 14, 2020
 *
 */
@Data
@NoArgsConstructor
public class AppointSchedulerVO {
	private Integer prior;
	private String template;
	
	/**
	 * For query checkForSchedulers in AppointmentDaoImpl
	 * 
	 * @param prior
	 * @param template
	 */
	public AppointSchedulerVO(Integer prior, String template) {
		this.prior = prior;
		this.template = template;
	}
	
}
