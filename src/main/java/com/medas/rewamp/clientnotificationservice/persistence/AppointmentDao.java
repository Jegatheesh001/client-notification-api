package com.medas.rewamp.clientnotificationservice.persistence;

import java.time.LocalDateTime;
import java.util.List;

import com.medas.rewamp.clientnotificationservice.business.vo.AppointSchedulerDataVO;
import com.medas.rewamp.clientnotificationservice.business.vo.AppointSchedulerVO;

/**
 *
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Jan 14, 2020
 *
 */
public interface AppointmentDao {

	List<AppointSchedulerVO> checkForSchedulers(LocalDateTime currentTime);

	List<AppointSchedulerDataVO> appointmentNotConfirmedPatientList(LocalDateTime currentTime, Integer prior);

}
