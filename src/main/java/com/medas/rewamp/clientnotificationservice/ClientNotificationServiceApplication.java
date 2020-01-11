package com.medas.rewamp.clientnotificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Jan 9, 2020
 *
 */
@SpringBootApplication
@EnableScheduling
public class ClientNotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientNotificationServiceApplication.class, args);
	}

}
