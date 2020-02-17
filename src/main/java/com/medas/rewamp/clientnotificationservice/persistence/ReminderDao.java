package com.medas.rewamp.clientnotificationservice.persistence;

import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderCreationVO;
import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderListVO;
import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderSearchVO;
import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderUpdationVO;

/**
 *
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Feb 17, 2020
 *
 */
public interface ReminderDao {

	boolean createReminder(ReminderCreationVO reminderVO);

	boolean updateReminder(ReminderUpdationVO reminderVO);

	ReminderListVO getAllReminders(ReminderSearchVO reminderVO);

}
