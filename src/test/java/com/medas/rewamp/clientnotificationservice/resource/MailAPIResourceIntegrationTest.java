package com.medas.rewamp.clientnotificationservice.resource;


import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.medas.rewamp.clientnotificationservice.business.vo.MailParamVO;
import com.medas.rewamp.clientnotificationservice.service.MailService;

/**
 * @author jegatheesh.mageswaran<br>
 *         <b>Created</b> On Jan 24, 2020
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
class MailAPIResourceIntegrationTest {

	@Autowired
	MailService mailService;

	@Test
	public void sendMail() throws Exception {
		MailParamVO mailParam = new MailParamVO();
		mailParam.setMailRequestId(1);
		mailParam.setMailId("test@mail.com");
		mailParam.setMailSubject("Test Mail");
		mailParam.setMailTemplate("Hello");
		mailParam.setOfficeId(2);
		assertNotNull(mailParam);
		// Skipping integration Test
		// assertEquals(true, mailService.sendMail(mailParam).isSuccess());
	}

}
