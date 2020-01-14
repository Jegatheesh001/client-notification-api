package com.medas.rewamp.clientnotificationservice.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.medas.rewamp.clientnotificationservice.business.constants.CommonConstants;
import com.medas.rewamp.clientnotificationservice.business.vo.ApiResponse;
import com.medas.rewamp.clientnotificationservice.business.vo.BirthdayDetailsVO;
import com.medas.rewamp.clientnotificationservice.business.vo.NotificationParamVO;
import com.medas.rewamp.clientnotificationservice.persistence.RegistratonDao;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Jan 11, 2020
 *
 */
@Slf4j
@Service
public class RegistrationService {
	
	private NotificationApiProxy proxy;
	private RegistratonDao dao;
	
	public RegistrationService(NotificationApiProxy proxy, RegistratonDao dao) {
		this.proxy = proxy;
		this.dao = dao;
	}

	@Value("${app.clientId}")
	private String clientId;
	
	@Value("${app.sms.birthday.office}")
	private Integer birthdayOffice;
	
	@Value("${app.sms.birthday.template}")
	private String birthdayTemplate;
	
	@Value("${app.reminder.birthday}")
	private Integer birthdayCheck;

	public List<BirthdayDetailsVO> getAllNextDayBirthDayPatients() {
		LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
		return dao.getBirthDayPatientsByCriteria(tomorrow);
	}

	public void sendBirthdayReminderToPatients() {
		if(birthdayCheck == 0) {
			throw new UnsupportedOperationException("Birthday Reminder Not Enabled");
		}
		LocalDateTime currentTime = LocalDateTime.now();
		// Setting Basic Details
		NotificationParamVO notificationVO = new NotificationParamVO("reg", null, CommonConstants.SMS, null, null, "Y",
				currentTime, currentTime, clientId, birthdayOffice != null ? birthdayOffice : 0);
		List<BirthdayDetailsVO> dataList = getAllNextDayBirthDayPatients();
		ApiResponse<Void> response = null;
		for(BirthdayDetailsVO data : dataList) {
			// Setting individual patient details
			notificationVO.setReferId(data.getOpId());
			notificationVO.setNotificationId(data.getMobileNo());
			notificationVO.setNotificationTemplate(getTemplate(birthdayTemplate, data));
			// pushing data to cloud
			response = proxy.saveAPI(notificationVO);
			if(!response.isSuccess()) {
				log.error("Notification Failed: " + response.getMessage());
			}
		}
	}

	/**
	 * Setting data to base template
	 * 
	 * @param birthdayTemplate
	 * @param data
	 * @return
	 */
	private String getTemplate(String birthdayTemplate, BirthdayDetailsVO data) {
		return birthdayTemplate.replace("#patientName#", data.getPatientName());
	}
}
