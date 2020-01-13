package com.medas.rewamp.clientnotificationservice.business.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Jan 13, 2020
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateVO {
	private String templateName;
	private String template;
	private Integer timeBefore;
}
