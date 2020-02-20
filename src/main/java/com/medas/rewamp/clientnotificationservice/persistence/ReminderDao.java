package com.medas.rewamp.clientnotificationservice.persistence;

import java.util.List;

import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderCreationVO;
import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderDetailsVO;
import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderSearchVO;
import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderUpdationVO;
import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderVO;

/**
 *
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Feb 17, 2020
 *
 */
public interface ReminderDao {

	boolean createReminder(ReminderCreationVO reminderVO);

	boolean updateReminder(ReminderUpdationVO reminderVO);

	List<ReminderVO> getAllReminders(ReminderSearchVO reminderVO);
	
	ReminderVO getReminderById(Integer reminderId);

	List<ReminderDetailsVO> getReminderDetails(Integer reminderId);

	boolean isReminderClosed(Integer reminderId);

	boolean closeReminder(Integer reminderId);

}
