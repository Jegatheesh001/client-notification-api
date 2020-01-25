package com.medas.rewamp.clientnotificationservice.resource;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medas.rewamp.clientnotificationservice.business.vo.ApiResponse;
import com.medas.rewamp.clientnotificationservice.business.vo.MailParamVO;
import com.medas.rewamp.clientnotificationservice.service.MailService;

/**
 * @author jegatheesh.mageswaran<br>
 *         <b>Created</b> On Jan 24, 2020
 *
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class MailAPIResourceTest {

	private MockMvc mockMvc;
	
	@Mock
	MailService mailService;
	
	@InjectMocks
	private MailAPIResource mailApiResource;
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(mailApiResource).build();
	}

	@Test
	public void sendMail() throws Exception {
		MailParamVO mailParam = new MailParamVO();
		mailParam.setMailRequestId(1);
		mailParam.setMailId("test@mail.com");
		mailParam.setMailSubject("Test Mail");
		mailParam.setMailTemplate("Hello");
		mailParam.setOfficeId(2);
		Mockito.when(mailService.sendMail(mailParam)).thenReturn(new ApiResponse<>(true, null));
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/client/mail")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(MockMvcResultMatchers.status().is4xxClientError()); // 405 method not exists
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/client/mail")
			      .contentType(MediaType.APPLICATION_JSON)
			      .content("{"
			      		+ "\"mailId\" : \"test@mail.com\","
			      		+ "\"mailSubject\" : \"Test Mail\","
			      		+ "\"mailTemplate\" : \"Hello\","
			      		+ "\"officeId\" : 2"
			      		+ "}"))
			      .andExpect(MockMvcResultMatchers.status().is4xxClientError()); // 400 mailRequestId missing
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/client/mail")
			      .contentType(MediaType.APPLICATION_JSON)
			      .content(new ObjectMapper().writeValueAsString(mailParam)))
			      .andExpect(MockMvcResultMatchers.status().isOk());
	}

}
