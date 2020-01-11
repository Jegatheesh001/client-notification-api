package com.medas.rewamp.clientnotificationservice.configuration;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Cron Job Scheduler
 *
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Jan 11, 2020
 *
 */
@Slf4j
@Component
public class CronJobTask {
	/**
	 * Will run every day at 8'o clock
	 */
	@Scheduled(cron = "0 0 8 * * *")
	public void birthdayCheck() {
		log.info("Scheduler Run Completed");
	}
}
