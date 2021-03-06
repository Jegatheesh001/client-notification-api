package com.medas.rewamp.clientnotificationservice.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.medas.rewamp.clientnotificationservice.business.constants.CommonConstants;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger Configuration
 * 
 * @author jegatheesh.mageswaran<br>
 *         <b>Created</b> On Jan 9, 2020
 *
 */
@EnableSwagger2
@Configuration
public class SwaggerConfiguration {
	/**
	 * Restricting swagger to look into mentioned paths <br>
	 * 
	 * @apiNote /v2/api-docs
	 * @return Docket
	 */
	@Bean
	public Docket swaggerConfig() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				// service package
				.apis(RequestHandlerSelectors.basePackage("com.medas.rewamp.clientnotificationservice"))
				// building docket
				.build()
				// Setting API Information
				.apiInfo(getAPIInfo());
	}

	private ApiInfo getAPIInfo() {
		return new ApiInfo("Client Notification Service",
				"API's to get real time events from eClinic and push data from eClinic", "1.0", "Internal Service",
				new Contact("Medas Middle East Software Systems", CommonConstants.NOT_AVAILABLE, CommonConstants.NOT_AVAILABLE),
				"API Licence", CommonConstants.NOT_AVAILABLE, Collections.emptyList());
	}
}
