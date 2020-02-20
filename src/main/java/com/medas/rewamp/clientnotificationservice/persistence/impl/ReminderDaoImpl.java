package com.medas.rewamp.clientnotificationservice.persistence.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
		String queryStr = "update ReminderHeader set followupBy=:followupBy, followupDate=:followupDate, lastUpdated=:currentTime "
				+ "where reminderId = :reminderId";
		em.createQuery(queryStr).setParameter(ReminderConstants.REMINDER_ID, reminderVO.getReminderId())
				.setParameter("followupBy", reminderVO.getFollowupBy())
				.setParameter("followupDate", reminderVO.getFollowupDate())
				.setParameter("currentTime", LocalDateTime.now()).executeUpdate();
		ReminderDetails reminder = new ReminderDetails(reminderVO);
		em.persist(reminder);
		return true;
	}

	@Override
	public List<ReminderVO> getAllReminders(ReminderSearchVO reminderVO) {
		String queryStr = "select new com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderVO(reminderId, reminderType, reminderReferId, "
				+ "createdBy, followupBy, followupDate, subject, contact, closedStatus) "
				+ "from ReminderHeader where reminderId > 0 ";
		StringBuilder queryBuilder = new StringBuilder(queryStr);
		Map<String, Object> params = new HashMap<>();
		setReminderSearchParams(reminderVO, queryBuilder, params);
		queryBuilder.append("order by closedStatus, reminderId desc");
		Query query = em.createQuery(queryBuilder.toString());
		params.forEach(query::setParameter);
		return query.getResultList();
	}
	
	@Override
	public ReminderVO getReminderById(Integer reminderId) {
		String queryStr = "select new com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderVO(reminderId, reminderType, reminderReferId, "
				+ "createdBy, followupBy, followupDate, subject, contact, closedStatus) "
				+ "from ReminderHeader where reminderId = :reminderId ";
		return (ReminderVO) em.createQuery(queryStr).setParameter("reminderId", reminderId).getSingleResult();
	}

	private void setReminderSearchParams(ReminderSearchVO reminderVO, StringBuilder queryBuilder,
			Map<String, Object> params) {
		if (reminderVO != null) {
			if (reminderVO.getReminderType() != null && !reminderVO.getReminderType().trim().isEmpty()) {
				queryBuilder.append("and reminderType=:reminderType ");
				params.put("reminderType", reminderVO.getReminderType());
			}
			if (reminderVO.getSubject() != null && !reminderVO.getSubject().trim().isEmpty()) {
				queryBuilder.append("and subject like :subject ");
				params.put("subject", "%" + reminderVO.getSubject() + "%");
			}
			if (reminderVO.getCreatedBy() != null && reminderVO.getCreatedBy() > 0) {
				queryBuilder.append("and createdBy=:createdBy ");
				params.put("createdBy", reminderVO.getCreatedBy());
			}
			if (reminderVO.getFollowupBy() != null && reminderVO.getFollowupBy() > 0) {
				queryBuilder.append("and followupBy=:followupBy ");
				params.put("followupBy", reminderVO.getFollowupBy());
			}
			if(reminderVO.getFollowupDate() != null) {
				queryBuilder.append("and followupDate=:followupDate ");
				params.put("followupDate", reminderVO.getFollowupDate());
			}
			if (reminderVO.getClosedStatus() != null && !reminderVO.getClosedStatus().trim().isEmpty()) {
				queryBuilder.append("and closedStatus=:closedStatus ");
				params.put("closedStatus", reminderVO.getClosedStatus());
			}
		}
	}

	@Override
	public List<ReminderDetailsVO> getReminderDetails(Integer reminderId) {
		String queryStr = "select new com.medas.rewamp.clientnotificationservice.business.vo.reminder.ReminderDetailsVO(remarks, followupDate, createdDate) "
				+ "from ReminderDetails where reminder.reminderId = :reminderId "
				+ "order by createdDate desc";
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
