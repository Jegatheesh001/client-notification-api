package com.medas.rewamp.clientnotificationservice.business.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * VO for Birthday Notification Query
 *
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Jan 11, 2020
 *
 */
@Data
@NoArgsConstructor
public class BirthdayDetailsVO {
	
	private Integer opId; // Primary key of Registration table
	private String patientName;
	private String mobileNo;
	
	/**
	 * For query getBirthDayPatientsByCriteria in RegistrationDaoImpl
	 * 
	 * @param opId
	 * @param patientName
	 * @param mobileNo
	 */
	public BirthdayDetailsVO(Integer opId, String patientName, String mobileNo) {
		this.opId = opId;
		this.patientName = patientName;
		this.mobileNo = mobileNo;
	}
	
}
