package com.medas.rewamp.clientnotificationservice.business.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 *
 * @author jegatheesh.mageswaran<br>
 *         <b>Created</b> On Jan 18, 2020
 *
 */
@Data
@Entity
@Table(name = "appointments")
public class Appointment {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer appointId;

	@Column(name = "appoint_type")
	private String appointType;

	@Column(name = "op_number")
	private String opNumber;

	@Column(name = "appoint_name")
	private String appointName;

	@Column(name = "appoint_date")
	private LocalDate appointDate;

	@Column(name = "appoint_hr")
	private String appointHr;

	@Column(name = "appoint_min")
	private String appointMin;

	@Column(name = "slot_nos")
	private String slotNos;

	@Column(name = "doctors_id")
	private Integer doctorsId;

	@Column(name = "department_id")
	private Integer departmentId;

	@Column(name = "office_id")
	private Integer officeId;

	@Column(name = "sex")
	private String gender;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@Column(name = "mobile_code")
	private String mobileCode;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "patient_email")
	private String email;

	@Column(name = "enteredby")
	private Integer enteredBy;

	@Column(name = "entry_date")
	private LocalDateTime entryDate;

	@Column(name = "cancel_status")
	private String cancelStatus;

	@Column(name = "cancel_date")
	private LocalDateTime cancelDate;

	@Column(name = "call_status")
	private String callStatus;

	@Column(name = "confirm_status")
	private String confirmStatus;

	@Column(name = "confirm_by")
	private Integer confirmBy;

	@Column(name = "confirm_date")
	private LocalDateTime confirmDate;

	@Column(name = "appoint_status")
	private String appointStatus;

	@Column(name = "bill_submit")
	private String billSubmit;

	@Column(name = "doctor_view")
	private String doctorView;

}
