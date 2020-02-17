package com.medas.rewamp.clientnotificationservice.persistence.impl;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.medas.rewamp.clientnotificationservice.business.entity.ReminderDetails;
import com.medas.rewamp.clientnotificationservice.business.entity.ReminderHeader;
import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderCreationVO;
import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderListVO;
import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderSearchVO;
import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderUpdationVO;
import com.medas.rewamp.clientnotificationservice.persistence.ReminderDao;

/**
 * Reminder resource related queries
 * 
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Feb 17, 2020
 *
 */
@Repository
public class ReminderDaoImpl implements ReminderDao {
	
	@PersistenceContext
	EntityManager em;

	@Override
	public boolean createReminder(ReminderCreationVO reminderVO) {
		ReminderHeader reminder = new ReminderHeader(reminderVO);
		ReminderDetails reminderDetails = new ReminderDetails(reminderVO);
		em.persist(reminder);
		reminderDetails.setReminder(new ReminderHeader(reminder.getReminderId()));
		em.persist(reminderDetails);
		return true;
	}

	@Override
	public boolean updateReminder(ReminderUpdationVO reminderVO) {
		String queryStr = "update ReminderHeader set followupDate=:followupDate, lastUpdated=:currentTime "
				+ "where reminderId = :reminderId";
		em.createQuery(queryStr).setParameter("reminderId", reminderVO.getReminderId())
				.setParameter("followupDate", reminderVO.getFollowupDate())
				.setParameter("currentTime", LocalDateTime.now()).executeUpdate();
		ReminderDetails reminder = new ReminderDetails(reminderVO);
		em.persist(reminder);
		return true;
	}

	@Override
	public ReminderListVO getAllReminders(ReminderSearchVO reminderVO) {
		return null;
	}

}
