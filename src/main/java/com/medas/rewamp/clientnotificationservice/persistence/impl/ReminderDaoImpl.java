package com.medas.rewamp.clientnotificationservice.persistence.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.medas.rewamp.clientnotificationservice.business.constants.ReminderConstants;
import com.medas.rewamp.clientnotificationservice.business.entity.ReminderDetails;
import com.medas.rewamp.clientnotificationservice.business.entity.ReminderHeader;
import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderCreationVO;
import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderDetailsVO;
import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderSearchVO;
import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderUpdationVO;
import com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderVO;
import com.medas.rewamp.clientnotificationservice.persistence.ReminderDao;

/**
 * Reminder resource related queries
 * 
 * @author jegatheesh.mageswaran<br>
 *         <b>Created</b> On Feb 17, 2020
 *
 */
@SuppressWarnings("unchecked")
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
		em.createQuery(queryStr).setParameter(ReminderConstants.REMINDER_ID, reminderVO.getReminderId())
				.setParameter("followupDate", reminderVO.getFollowupDate())
				.setParameter("currentTime", LocalDateTime.now()).executeUpdate();
		ReminderDetails reminder = new ReminderDetails(reminderVO);
		em.persist(reminder);
		return true;
	}

	@Override
	public List<ReminderVO> getAllReminders(ReminderSearchVO reminderVO) {
		String queryStr = "select new com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderVO(reminderType, reminderReferId, "
				+ "createdBy, followupBy, followupDate, subject, contact) "
				+ "from ReminderHeader where reminderId > 0 ";
		return em.createQuery(queryStr).getResultList();
	}

	@Override
	public List<ReminderDetailsVO> getReminderDetails(Integer reminderId) {
		String queryStr = "select new com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderDetailsVO(remarks, followupDate, createdDate) "
				+ "from ReminderDetails where reminder.reminderId = :reminderId";
		return em.createQuery(queryStr).setParameter(ReminderConstants.REMINDER_ID, reminderId).getResultList();
	}
	
	@Override
	public boolean isReminderClosed(Integer reminderId) {
		String queryStr = "select count(*) from ReminderHeader "
				+ "where reminderId = :reminderId and closedStatus = 'N' ";
		return (long) em.createQuery(queryStr).setParameter(ReminderConstants.REMINDER_ID, reminderId).getSingleResult() == 0;
	}

	@Override
	public boolean closeReminder(Integer reminderId) {
		String queryStr = "update ReminderHeader set closedStatus = 'Y', lastUpdated=:currentTime "
				+ "where reminderId = :reminderId";
		em.createQuery(queryStr).setParameter(ReminderConstants.REMINDER_ID, reminderId)
				.setParameter("currentTime", LocalDateTime.now()).executeUpdate();
		return true;
	}

}
