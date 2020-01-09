package com.medas.rewamp.clientnotificationservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author jegatheesh.mageswaran<br>
 *         <b>Created</b> On Jan 9, 2020
 *
 */
@Configuration
public class RestTemplateConfiguration {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
