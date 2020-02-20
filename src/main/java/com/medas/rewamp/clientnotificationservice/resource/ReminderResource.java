package com.medas.rewamp.clientnotificationservice.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medas.rewamp.clientnotificationservice.business.vo.ApiResponse;
import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderCreationVO;
import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderDetailsVO;
import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderSearchVO;
import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderUpdationVO;
import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderVO;
import com.medas.rewamp.clientnotificationservice.configuration.aspects.Loggable;
import com.medas.rewamp.clientnotificationservice.service.ReminderService;

/**
 * For Reminder related api's
 * 
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Feb 17, 2020
 *
 */
@RestController
@RequestMapping("/v1/client/reminder")
public class ReminderResource {
	
	@Autowired
	ReminderService service;
	
	@Loggable
	@PostMapping
	public ApiResponse<Void> createReminder(@Valid @RequestBody ReminderCreationVO reminderVO) {
		return service.createReminder(reminderVO);
	}
	
	@Loggable
	@PutMapping
	public ApiResponse<Void> updateReminder(@Valid @RequestBody ReminderUpdationVO reminderVO) {
		return service.updateReminder(reminderVO);
	}
	
	@Loggable
	@GetMapping
	public ApiResponse<List<ReminderVO>> getAllReminders(@Valid ReminderSearchVO reminderVO) {
		return service.getAllReminders(reminderVO);
	}
	
	@Loggable
	@GetMapping("/{reminderId}")
	public ApiResponse<ReminderVO> getReminderById(@PathVariable Integer reminderId) {
		return service.getReminderById(reminderId);
	}
	
	@Loggable
	@GetMapping("/{reminderId}/details")
	public ApiResponse<List<ReminderDetailsVO>> getReminderDetails(@PathVariable Integer reminderId) {
		return service.getReminderDetails(reminderId);
	}
	
	@Loggable
	@PutMapping("/{reminderId}/close")
	public ApiResponse<Void> closeReminder(@PathVariable Integer reminderId) {
		return service.closeReminder(reminderId);
	}
}
