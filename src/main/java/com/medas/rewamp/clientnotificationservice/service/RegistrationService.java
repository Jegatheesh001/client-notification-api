package com.medas.rewamp.clientnotificationservice.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medas.rewamp.clientnotificationservice.business.vo.BirthdayDetailsVO;
import com.medas.rewamp.clientnotificationservice.persistence.RegistratonDao;

/**
 *
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Jan 11, 2020
 *
 */
@Service
public class RegistrationService {
	
	@Autowired
	RegistratonDao dao;

	public List<BirthdayDetailsVO> getAllNextDayBirthDayPatients() {
		LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
		return dao.getBirthDayPatientsByCriteria(tomorrow);
	}
}
