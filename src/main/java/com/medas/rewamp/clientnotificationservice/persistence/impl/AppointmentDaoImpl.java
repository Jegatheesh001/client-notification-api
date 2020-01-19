package com.medas.rewamp.clientnotificationservice.persistence.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.medas.rewamp.clientnotificationservice.business.constants.AppointCallStatus;
import com.medas.rewamp.clientnotificationservice.business.vo.AppointSchedulerDataVO;
import com.medas.rewamp.clientnotificationservice.business.vo.AppointSchedulerVO;
import com.medas.rewamp.clientnotificationservice.persistence.AppointmentDao;

@SuppressWarnings("unchecked")
@Repository
public class AppointmentDaoImpl implements AppointmentDao {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<AppointSchedulerVO> checkForSchedulers(LocalDateTime currentTime) {
		String queryStr = "select new com.medas.rewamp.clientnotificationservice.business.vo.AppointSchedulerVO(prior, template) "
				+ "from AppointmentScheduler where runAt = :scheduleTime";
		return em.createQuery(queryStr).setParameter("scheduleTime", currentTime.toLocalTime()).getResultList();
	}

	@Override
	public List<AppointSchedulerDataVO> appointmentNotConfirmedPatientList(LocalDateTime currentTime, Integer prior) {
		String queryStr = "select new com.medas.rewamp.clientnotificationservice.business.vo.AppointSchedulerDataVO(appointId, "
				+ "appointName, concat(mobileCode, mobile), appointDate, officeId) "
				+ "from Appointment where appointDate = :appointDate and cancelStatus = 'N' and confirmStatus = 'N' "
				+ "and callStatus is not null and callStatus != :confirmed and callStatus != :cancelled";
		return em.createQuery(queryStr).setParameter("appointDate", currentTime.plusDays(prior).toLocalDate())
				.setParameter("confirmed", AppointCallStatus.CONFIRMED)
				.setParameter("cancelled", AppointCallStatus.CANCELLED).getResultList();
	}

}
