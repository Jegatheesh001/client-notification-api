package com.medas.rewamp.clientnotificationservice.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medas.rewamp.clientnotificationservice.business.vo.ApiResponse;
import com.medas.rewamp.clientnotificationservice.business.vo.AppointParamVO;
import com.medas.rewamp.clientnotificationservice.configuration.aspects.Loggable;
import com.medas.rewamp.clientnotificationservice.service.AppointmentService;

import io.swagger.annotations.ApiOperation;

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

	@Loggable
	@PostMapping("/{appointId}")
	@ApiOperation(value = "API to capture save trigger from client", notes = "Provide all the parameters(oldAppointId is not needed)")
	public ApiResponse<Void> newAppointmentTrigger(@RequestBody AppointParamVO paramVO) {
		return service.registerNewAppointment(paramVO);
	}
	
	@Loggable
	@PutMapping("/cancel/{appointId}")
	@ApiOperation(value = "API to capture cancel trigger from client", notes = "Provide appointId and officeId")
	public ApiResponse<Void> cancelAppointmentTrigger(@RequestBody AppointParamVO paramVO) {
		return service.cancelAppointment(paramVO);
	}
	
	@Loggable
	@PutMapping("/update/{appointId}")
	@ApiOperation(value = "API to capture update trigger from client", notes = "Provide all the parameters")
	public ApiResponse<Void> updateAppointmentTrigger(@RequestBody AppointParamVO paramVO) {
		return service.updateAppointment(paramVO);
	}
	
	@Loggable
	@PostMapping("/reschedule/{appointId}")
	@ApiOperation(value = "API to capture reschedule trigger from client", notes = "Provide all the parameters")
	public ApiResponse<Void> rescheduleAppointmentTrigger(@RequestBody AppointParamVO paramVO) {
		return service.rescheduleAppointment(paramVO);
	}
	
	@Loggable
	@PostMapping("/sendSms")
	@ApiOperation(value = "API to capture direct sms trigger from client", notes = "Provide all the parameters(oldAppointId is not needed)")
	public ApiResponse<Void> sendSms(@RequestBody AppointParamVO paramVO) {
		return service.sendSms(paramVO);
	}
	
}
