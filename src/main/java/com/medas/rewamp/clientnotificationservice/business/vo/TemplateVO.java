package com.medas.rewamp.clientnotificationservice.business.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "SMS Template object")
public class TemplateVO {
	
	@ApiModelProperty(notes = "Template Name")
	private String templateName;
	@ApiModelProperty(notes = "Template details")
	private String template;
	@ApiModelProperty(notes = "Specify time for scheduled notification")
	private Integer timeBefore;
	
}
