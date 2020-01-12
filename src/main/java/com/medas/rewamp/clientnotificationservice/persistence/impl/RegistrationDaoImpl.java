package com.medas.rewamp.clientnotificationservice.persistence.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.medas.rewamp.clientnotificationservice.business.vo.BirthdayDetailsVO;
import com.medas.rewamp.clientnotificationservice.persistence.RegistratonDao;

/**
 *
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Jan 11, 2020
 *
 */
@Repository
public class RegistrationDaoImpl implements RegistratonDao {
	
	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<BirthdayDetailsVO> getBirthDayPatientsByCriteria(LocalDateTime date) {
		String queryStr = "select new com.medas.rewamp.clientnotificationservice.business.vo.BirthdayDetailsVO(registrationId, patientName, concat(mobileCode, mobile)) "
				+ "from Registration where function('day', dateOfBirth) = :day and function('month', dateOfBirth) =:month ";
		return em.createQuery(queryStr).setParameter("day", date.getDayOfMonth()).setParameter("month", date.getMonthValue()).getResultList();
	}

}
