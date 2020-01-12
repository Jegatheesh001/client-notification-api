package com.medas.rewamp.clientnotificationservice.business.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BirthdayDetailsVO {
	
	private Integer opId; // Primary key of Registration table
	private String patientName;
	private String mobileNo;
	
}
