package com.medas.rewamp.clientnotificationservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.medas.rewamp.clientnotificationservice.business.vo.ApiResponseVO;
import com.medas.rewamp.clientnotificationservice.business.vo.NotificationParamVO;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Jan 9, 2020
 *
 */
@Slf4j
@Service
public class NotificationApiProxy {

	private RestTemplate template;

	public NotificationApiProxy(RestTemplate template) {
		this.template = template;
	}

	@Value("${app.link.notification-api}")
	private String notificationApiLink;

	public ApiResponseVO<Void> saveAPI(NotificationParamVO notificationVO) {
		try {
			ResponseEntity<ApiResponseVO<Void>> reponse = template.exchange(notificationApiLink + "/notification",
					HttpMethod.POST, new HttpEntity<>(notificationVO),
					new ParameterizedTypeReference<ApiResponseVO<Void>>() { });
			return reponse.getBody();
		} catch (Exception e) {
			log.error("Error on pushing message to cloud: " + e.getMessage());
			return new ApiResponseVO<>(false, "Connection issue");
		}
	}
	
	public ApiResponseVO<Void> cancelAPI(NotificationParamVO notificationVO) {
		try {
			ResponseEntity<ApiResponseVO<Void>> reponse = template.exchange(notificationApiLink + "/notification/" 
					+ notificationVO.getReferId(), HttpMethod.PUT,
					new HttpEntity<>(notificationVO), new ParameterizedTypeReference<ApiResponseVO<Void>>() { });
			return reponse.getBody();
		} catch (Exception e) {
			log.error("Error on pushing message to cloud: " + e.getMessage());
			return new ApiResponseVO<>(false, "Connection issue");
		}
	}
}
