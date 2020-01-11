package com.medas.rewamp.clientnotificationservice.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medas.rewamp.clientnotificationservice.business.vo.ApiResponse;
import com.medas.rewamp.clientnotificationservice.business.vo.AppointParamVO;
import com.medas.rewamp.clientnotificationservice.service.AppointmentService;

/**
 * To capture client appointment real-time triggers
 *
 * @author jegatheesh.mageswaran<br>
 *         <b>Created</b> On Jan 9, 2020
 *
 */
@RestController
@RequestMapping("/v1/client/appointment")
public class AppointmentResource {
	
	@Autowired
	AppointmentService service;

	@PostMapping("/{appointId}")
	public ApiResponse<Void> newAppointmentTrigger(@RequestBody AppointParamVO paramVO) {
		return service.registerNewAppointment(paramVO);
	}
	
	@PostMapping("/cancel/{appointId}")
	public ApiResponse<Void> cancelAppointmentTrigger(@RequestBody AppointParamVO paramVO) {
		return service.cancelAppointment(paramVO);
	}
	
	@PostMapping("/reschedule/{appointId}")
	public ApiResponse<Void> rescheduleAppointmentTrigger(@RequestBody AppointParamVO paramVO) {
		return service.rescheduleAppointment(paramVO);
	}
	
	@PostMapping("/sendSms")
	public ApiResponse<Void> sendSms(@RequestBody AppointParamVO paramVO) {
		return service.sendSms(paramVO);
	}
	
}
