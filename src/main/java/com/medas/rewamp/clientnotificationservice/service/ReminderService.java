package com.medas.rewamp.clientnotificationservice.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.medas.rewamp.clientnotificationservice.business.vo.ApiResponse;
import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderCreationVO;
import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderDetailsVO;
import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderSearchVO;
import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderUpdationVO;
import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderVO;
import com.medas.rewamp.clientnotificationservice.persistence.ReminderDao;

import lombok.AllArgsConstructor;

/**
 * For Reminder related service
 *
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Feb 17, 2020
 *
 */
@Service
@AllArgsConstructor
public class ReminderService {
	
	ReminderDao dao;

	@Transactional
	public ApiResponse<Void> createReminder(ReminderCreationVO reminderVO) {
		boolean success = dao.createReminder(reminderVO);
		return new ApiResponse<>(success, "Reminder Created Successfully");
	}

	@Transactional
	public ApiResponse<Void> updateReminder(ReminderUpdationVO reminderVO) {
		boolean success = dao.updateReminder(reminderVO);
		return new ApiResponse<>(success, "Reminder Updated Successfully");
	}

	public ApiResponse<List<ReminderVO>> getAllReminders(@Valid ReminderSearchVO reminderVO) {
		return new ApiResponse<>(dao.getAllReminders(reminderVO));
	}

	public ApiResponse<ReminderVO> getReminderById(Integer reminderId) {
		return new ApiResponse<>(dao.getReminderById(reminderId));
	}

	public ApiResponse<List<ReminderDetailsVO>> getReminderDetails(Integer reminderId) {
		return new ApiResponse<>(dao.getReminderDetails(reminderId));
	}

	@Transactional
	public ApiResponse<Void> closeReminder(Integer reminderId) {
		if (dao.isReminderClosed(reminderId)) {
			return new ApiResponse<>(true, "Reminder Already Closed");
		}
		boolean success = dao.closeReminder(reminderId);
		return new ApiResponse<>(success, "Reminder Closed Successfully");
	}

}
