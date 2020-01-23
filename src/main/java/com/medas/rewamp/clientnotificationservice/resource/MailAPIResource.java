package com.medas.rewamp.clientnotificationservice.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medas.rewamp.clientnotificationservice.business.vo.ApiResponse;
import com.medas.rewamp.clientnotificationservice.business.vo.MailParamVO;
import com.medas.rewamp.clientnotificationservice.configuration.aspects.Loggable;
import com.medas.rewamp.clientnotificationservice.service.MailService;

/**
 *
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Jan 23, 2020
 *
 */
@RestController
@RequestMapping("/v1/client/mail")
public class MailAPIResource {
	
	@Autowired
	MailService mailService;

	@Loggable
	@PostMapping
	public ApiResponse<Void> sendMail(@Valid @RequestBody MailParamVO mailParam) {
		return mailService.sendMail(mailParam);
	}
	
}
