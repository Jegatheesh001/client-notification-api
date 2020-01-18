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
public class AppointSchedulerDataVO {
	private Integer appointId;
	private String patientName;
	private String mobileNo;
	private Integer officeId;
	
	/**
	 * For query appointmentNotConfirmedPatientList in AppointmentDaoImpl
	 * 
	 * @param appointId
	 * @param patientName
	 * @param mobileNo
	 * @param officeId
	 */
	public AppointSchedulerDataVO(Integer appointId, String patientName, String mobileNo, Integer officeId) {
		super();
		this.appointId = appointId;
		this.patientName = patientName;
		this.mobileNo = mobileNo;
		this.officeId = officeId;
	}
	
}
