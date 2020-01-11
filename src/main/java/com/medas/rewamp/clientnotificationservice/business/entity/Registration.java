package com.medas.rewamp.clientnotificationservice.business.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author jegatheesh.mageswaran<br>
		   <b>Created</b> On Jan 11, 2020
 *
 */
@Data
@Entity
@Table(name = "new_registration")
public class Registration {

	@Id
	@GeneratedValue
	@Column(name = "op_id")
	private Integer registrationId;
	
	@Column(name = "op_number")
	private String opNumber;
	
	@Column(name = "patient_name")
	private String patientName;
	
	@Column(name = "emirates_id")
	private String emiratesId;
	
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;
	
	@Column(name = "registration_date")
	private LocalDate registrationDate;
	
	@Column(name = "registration_time")
	private LocalTime registrationTime;
	
	@Column(name = "sex")
	private String gender;
	
	@Column(name="mobile_code")
	private String mobileCode;
	
	@Column(name="mobile")
	private String mobile;
	
	@Column(name="patient_email")
	private String email;
	
}
