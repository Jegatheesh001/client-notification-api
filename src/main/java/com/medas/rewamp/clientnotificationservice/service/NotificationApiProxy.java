package com.medas.rewamp.clientnotificationservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.medas.rewamp.clientnotificationservice.business.vo.ApiResponse;
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

	public ApiResponse<Void> saveAPI(NotificationParamVO notificationVO) {
		try {
			ResponseEntity<ApiResponse<Void>> reponse = template.exchange(notificationApiLink + "/notification",
					HttpMethod.POST, new HttpEntity<>(notificationVO),
					new ParameterizedTypeReference<ApiResponse<Void>>() { });
			return reponse.getBody();
		} catch (Exception e) {
			log.error("Error on pushing message to cloud: " + e.getMessage());
			return new ApiResponse<>(false, "Issue: " + e.getMessage());
		}
	}
	
	public ApiResponse<Void> cancelAPI(NotificationParamVO notificationVO) {
		try {
			ResponseEntity<ApiResponse<Void>> reponse = template.exchange(notificationApiLink + "/notification/" 
					+ notificationVO.getReferId(), HttpMethod.PUT,
					new HttpEntity<>(notificationVO), new ParameterizedTypeReference<ApiResponse<Void>>() { });
			return reponse.getBody();
		} catch (Exception e) {
			log.error("Error on pushing message to cloud: " + e.getMessage());
			return new ApiResponse<>(false, "Issue: " + e.getMessage());
		}
	}
}
