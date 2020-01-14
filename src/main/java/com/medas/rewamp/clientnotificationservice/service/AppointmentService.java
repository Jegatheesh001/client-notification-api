package com.medas.rewamp.clientnotificationservice.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.medas.rewamp.clientnotificationservice.business.constants.CommonConstants;
import com.medas.rewamp.clientnotificationservice.business.vo.ApiResponse;
import com.medas.rewamp.clientnotificationservice.business.vo.AppointParamVO;
import com.medas.rewamp.clientnotificationservice.business.vo.NotificationParamVO;
import com.medas.rewamp.clientnotificationservice.business.vo.TemplateVO;

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

	public ApiResponse<Void> registerNewAppointment(AppointParamVO paramVO) {
		return pushNewAppointmentToCloud(paramVO);
	}

	private ApiResponse<Void> pushNewAppointmentToCloud(AppointParamVO paramVO) {
		LocalDateTime currentTime = LocalDateTime.now();
		LocalDateTime notificationTime = currentTime;

		NotificationParamVO notificationVO = new NotificationParamVO("app", paramVO.getAppointId(), CommonConstants.SMS,
				paramVO.getMobileNo(), paramVO.getAppointTemplate(), null, notificationTime, currentTime, clientId,
				paramVO.getOfficeId());
		ApiResponse<Void> out = null;
		if (paramVO.getAppointTemplate() != null) {
			// Instant Message
			notificationVO.setInstant("Y");
			out = proxy.saveAPI(notificationVO);
			log.info(out.toString());
		}
		if (paramVO.getTemplates() != null && !paramVO.getTemplates().isEmpty()) {
			for (TemplateVO template : paramVO.getTemplates()) {
				notificationTime = paramVO.getAppointTime().minusHours(template.getTimeBefore());
				if (notificationTime.isAfter(currentTime)) {
					notificationVO.setInstant("N");
					notificationVO.setNotificationTime(notificationTime);
					notificationVO.setNotificationTemplate(template.getTemplate());
					out = proxy.saveAPI(notificationVO);
					log.info(out.toString());
				}
			}
		}
		return out;
	}

	public ApiResponse<Void> cancelAppointment(AppointParamVO paramVO) {
		return pushCancelAppointmentToCloud(paramVO, paramVO.getAppointId());
	}

	private ApiResponse<Void> pushCancelAppointmentToCloud(AppointParamVO paramVO, Integer appointId) {
		LocalDateTime currentTime = LocalDateTime.now();
		NotificationParamVO notificationVO = new NotificationParamVO("app", appointId, CommonConstants.SMS, null, null, "Y",
				null, currentTime, clientId, paramVO.getOfficeId());
		return proxy.cancelAPI(notificationVO);
	}

	public ApiResponse<Void> updateAppointment(AppointParamVO paramVO) {
		pushCancelAppointmentToCloud(paramVO, paramVO.getAppointId());
		return pushNewAppointmentToCloud(paramVO);
	}

	public ApiResponse<Void> rescheduleAppointment(AppointParamVO paramVO) {
		pushCancelAppointmentToCloud(paramVO, paramVO.getOldAppointId());
		return pushNewAppointmentToCloud(paramVO);
	}

	public ApiResponse<Void> sendSms(AppointParamVO paramVO) {
		// Instant Message
		LocalDateTime currentTime = LocalDateTime.now();
		NotificationParamVO notificationVO = new NotificationParamVO("app", paramVO.getAppointId(), CommonConstants.SMS,
				paramVO.getMobileNo(), paramVO.getAppointTemplate(), "Y", currentTime, currentTime, clientId,
				paramVO.getOfficeId());
		ApiResponse<Void> out = proxy.saveAPI(notificationVO);
		log.info(out.toString());
		return out;
	}

}
