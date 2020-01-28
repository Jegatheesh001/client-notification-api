package com.medas.rewamp.clientnotificationservice.resource;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.medas.rewamp.clientnotificationservice.business.vo.MailParamVO;
import com.medas.rewamp.clientnotificationservice.service.MailService;

/**
 * @author jegatheesh.mageswaran<br>
 *         <b>Created</b> On Jan 24, 2020
 *
 */
@Disabled
@SpringBootTest
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
		assertEquals(true, mailService.sendMail(mailParam).isSuccess());
	}

}
