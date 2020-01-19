package com.medas.rewamp.clientnotificationservice.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.medas.rewamp.clientnotificationservice.business.constants.CommonConstants;
import com.medas.rewamp.clientnotificationservice.business.vo.ApiResponse;
import com.medas.rewamp.clientnotificationservice.business.vo.AppointParamVO;
import com.medas.rewamp.clientnotificationservice.business.vo.AppointSchedulerDataVO;
import com.medas.rewamp.clientnotificationservice.business.vo.AppointSchedulerVO;
import com.medas.rewamp.clientnotificationservice.business.vo.NotificationParamVO;
import com.medas.rewamp.clientnotificationservice.business.vo.TemplateVO;
import com.medas.rewamp.clientnotificationservice.persistence.AppointmentDao;
import com.medas.rewamp.clientnotificationservice.utils.DateUtil;

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
	
	private AppointmentDao dao;

	public AppointmentService(NotificationApiProxy proxy, AppointmentDao dao) {
		super();
		this.proxy = proxy;
		this.dao = dao;
	}

	@Value("${app.clientId}")
	private String clientId;
	
	@Value("${app.reminder.sms.appUnconfirmed}")
	private Integer appUnconfirmed;

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
		pushNewAppointmentToCloud(paramVO);
		return new ApiResponse<>(true, null);
	}

	public ApiResponse<Void> rescheduleAppointment(AppointParamVO paramVO) {
		pushCancelAppointmentToCloud(paramVO, paramVO.getOldAppointId());
		pushNewAppointmentToCloud(paramVO);
		return new ApiResponse<>(true, null);
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

	public void appointmentNotConfirmedPatientCheck() {
		if(appUnconfirmed == 0) {
			throw new UnsupportedOperationException("Appointment Unconfirmed Reminder Not Enabled");
		}
		LocalDateTime currentTime = LocalDateTime.now();
		List<AppointSchedulerVO> scheduleList = dao.checkForSchedulers(currentTime);
		log.info("Total Schedulers: {}", scheduleList.size());
		String template = null;
		for (AppointSchedulerVO scheduler : scheduleList) {
			List<AppointSchedulerDataVO> dataList = dao.appointmentNotConfirmedPatientList(currentTime, scheduler.getPrior());
			template = scheduler.getTemplate();
			log.info("Scheduler on {} prior {}", currentTime, scheduler.getPrior());
			log.info("Total: {} -> template: {}", dataList.size(), template);
			for (AppointSchedulerDataVO data : dataList) {
				// Instant Message
				NotificationParamVO notificationVO = new NotificationParamVO("app", data.getAppointId(),
						CommonConstants.SMS, data.getMobileNo(), fillTemplateWithData(template, data), "Y", currentTime, currentTime, clientId,
						data.getOfficeId());
				ApiResponse<Void> out = proxy.saveAPI(notificationVO);
				if(out.isSuccess())
					log.info("Data: {} | Response: {}", notificationVO, out.toString());
				else
					log.error("Data: {} | Response: {}", notificationVO, out.toString());
			}
		}
	}

	private String fillTemplateWithData(String template, AppointSchedulerDataVO data) {
		return template.replace("#patientName#", data.getPatientName()).replace("#appointDate#", DateUtil.formatDate("1", data.getAppointDate()));
	}

}
