package com.medas.rewamp.clientnotificationservice.persistence;

import java.time.LocalDateTime;
import java.util.List;

import com.medas.rewamp.clientnotificationservice.business.vo.BirthdayDetailsVO;

/**
 *
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Jan 11, 2020
 *
 */
public interface RegistratonDao {

	List<BirthdayDetailsVO> getBirthDayPatientsByCriteria(LocalDateTime date);

}
