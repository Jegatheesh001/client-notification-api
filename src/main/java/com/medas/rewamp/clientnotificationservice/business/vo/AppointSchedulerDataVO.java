package com.medas.rewamp.clientnotificationservice.business.vo;

import java.time.LocalDate;
import java.util.Date;

import com.medas.rewamp.clientnotificationservice.utils.DateUtil;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data for Appointment Unconfirmed Reminder SMS
 * 
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
	private Date appointDate;
	
	/**
	 * For query appointmentNotConfirmedPatientList in AppointmentDaoImpl
	 * 
	 * @param appointId
	 * @param patientName
	 * @param mobileNo
	 * @param appointDate
	 * @param officeId
	 */
	public AppointSchedulerDataVO(Integer appointId, String patientName, String mobileNo, LocalDate appointDate, Integer officeId) {
		super();
		this.appointId = appointId;
		this.patientName = patientName;
		this.mobileNo = mobileNo;
		if (appointDate != null) {
			this.appointDate = DateUtil.parseLDtoDate(appointDate);
		}
		this.officeId = officeId;
	}
	
}
