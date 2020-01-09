package com.medas.rewamp.clientnotificationservice.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.medas.rewamp.clientnotificationservice.business.constants.CommonConstants;
import com.medas.rewamp.clientnotificationservice.business.vo.ApiResponseVO;
import com.medas.rewamp.clientnotificationservice.business.vo.AppointParamVO;
import com.medas.rewamp.clientnotificationservice.business.vo.NotificationParamVO;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author jegatheesh.mageswaran<br>
 *         <b>Created</b> On Jan 9, 2020
 *
 */
@Slf4j
@Service
public class AppointmentService {

	private NotificationApiProxy proxy;

	public AppointmentService(NotificationApiProxy proxy) {
		super();
		this.proxy = proxy;
	}

	@Value("${app.clientId}")
	private String clientId;

	public ApiResponseVO<Void> registerNewAppointment(AppointParamVO paramVO) {
		return pushNewAppointmentToCloud(paramVO);
	}

	private ApiResponseVO<Void> pushNewAppointmentToCloud(AppointParamVO paramVO) {
		LocalDateTime currentTime = LocalDateTime.now();
		LocalDateTime notificationTime = currentTime;

		NotificationParamVO notificationVO = new NotificationParamVO("app", paramVO.getAppointId(), CommonConstants.SMS,
				paramVO.getMobileNo(), paramVO.getAppointTemplate(), notificationTime, currentTime, clientId,
				paramVO.getOfficeId());
		// Instant Message
		ApiResponseVO<Void> out = proxy.saveAPI(notificationVO);
		log.info(out.toString());
		// Before 24 hours
		notificationTime = paramVO.getAppointTime().minusHours(24);
		if (notificationTime.isAfter(currentTime)) {
			notificationVO.setNotificationTime(notificationTime);
			out = proxy.saveAPI(notificationVO);
			log.info(out.toString());
		}
		// Before 8 hours
		notificationTime = paramVO.getAppointTime().minusHours(8);
		if (notificationTime.isAfter(currentTime)) {
			notificationVO.setNotificationTime(notificationTime);
			out = proxy.saveAPI(notificationVO);
			log.info(out.toString());
		}
		return out;
	}

	public ApiResponseVO<Void> cancelAppointment(AppointParamVO paramVO) {
		return pushCancelAppointmentToCloud(paramVO, paramVO.getAppointId());
	}

	private ApiResponseVO<Void> pushCancelAppointmentToCloud(AppointParamVO paramVO, Integer appointId) {
		LocalDateTime currentTime = LocalDateTime.now();
		NotificationParamVO notificationVO = new NotificationParamVO("app", appointId, CommonConstants.SMS, null, null,
				null, currentTime, clientId, paramVO.getOfficeId());
		return proxy.cancelAPI(notificationVO);
	}

	public ApiResponseVO<Void> rescheduleAppointment(AppointParamVO paramVO) {
		pushCancelAppointmentToCloud(paramVO, paramVO.getOldAppointId());
		return pushNewAppointmentToCloud(paramVO);
	}

	public ApiResponseVO<Void> sendSms(AppointParamVO paramVO) {
		// Instant Message
		LocalDateTime currentTime = LocalDateTime.now();
		NotificationParamVO notificationVO = new NotificationParamVO("app", paramVO.getAppointId(), CommonConstants.SMS,
				paramVO.getMobileNo(), paramVO.getAppointTemplate(), currentTime, currentTime, clientId,
				paramVO.getOfficeId());
		ApiResponseVO<Void> out = proxy.saveAPI(notificationVO);
		log.info(out.toString());
		return out;
	}

}
