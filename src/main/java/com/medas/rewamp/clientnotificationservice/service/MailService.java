package com.medas.rewamp.clientnotificationservice.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.medas.rewamp.clientnotificationservice.business.constants.CommonConstants;
import com.medas.rewamp.clientnotificationservice.business.vo.ApiResponse;
import com.medas.rewamp.clientnotificationservice.business.vo.AttachmentParamVO;
import com.medas.rewamp.clientnotificationservice.business.vo.MailParamVO;
import com.medas.rewamp.clientnotificationservice.business.vo.NotificationAttachmentVO;
import com.medas.rewamp.clientnotificationservice.business.vo.NotificationParamVO;
import com.medas.rewamp.clientnotificationservice.utils.FileUtil;

/**
 * Mail service logic
 * 
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Jan 23, 2020
 *
 */
@Service
public class MailService {
	
	@Autowired
	private NotificationApiProxy proxy;
	
	@Autowired
	private FileUtil fileUtil;
	
	@Value("${app.clientId}")
	private String clientId;

	public ApiResponse<Void> sendMail(MailParamVO mailParam) {
		List<NotificationAttachmentVO> attachments = getAttachments(mailParam.getAttachments());
		LocalDateTime currentTime = LocalDateTime.now();
		NotificationParamVO notificationVO = new NotificationParamVO("lab", mailParam.getMailRequestId(),
				CommonConstants.EMAIL, mailParam.getMailId(), mailParam.getMailTemplate(), mailParam.getMailSubject(),
				"Y", currentTime, currentTime, clientId, mailParam.getOfficeId(), attachments);
		return proxy.saveAPI(notificationVO);
	}

	private List<NotificationAttachmentVO> getAttachments(List<AttachmentParamVO> mailAttachments) {
		List<NotificationAttachmentVO> attachments = null;
		if (mailAttachments != null && !mailAttachments.isEmpty()) {
			attachments = new ArrayList<>();
			for (AttachmentParamVO mailAttachment : mailAttachments) {
				String filePath = mailAttachment.getAttachmentPath();
				String fileExtension = null;
				if (filePath.contains(CommonConstants.DOT)) {
					int index = filePath.lastIndexOf(CommonConstants.DOT);
					fileExtension = filePath.substring(index + 1);
				} else {
					fileExtension = "jpg";
				}
				NotificationAttachmentVO attachment = new NotificationAttachmentVO(mailAttachment.getAttachmentType(),
						mailAttachment.getAttachmentName(),
						fileUtil.getBase64FromFile(mailAttachment.getAttachmentPath()), fileExtension);
				attachments.add(attachment);
			}
		}
		return attachments;
	}

}
