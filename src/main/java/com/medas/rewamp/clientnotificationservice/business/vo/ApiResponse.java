package com.medas.rewamp.clientnotificationservice.business.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * API Response object
 * 
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Jan 9, 2020
 *
 * @param <T>
 */
@Data
@ApiModel(description = "API Response object")
public class ApiResponse<T> {
	
	@ApiModelProperty(notes = "Response Status")
	private boolean success;
	@ApiModelProperty(notes = "Error message, if any.")
	private String message;
	@ApiModelProperty(notes = "Response Data")
	private T data;
	
	public ApiResponse() {
		super();
	}
	public ApiResponse(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}
}
