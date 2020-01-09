package com.medas.rewamp.clientnotificationservice.business.vo;

import lombok.Data;

/**
 *
 * @author jegatheesh.mageswaran<br>
 *		   <b>Created</b> On Jan 9, 2020
 *
 * @param <T>
 */
@Data
public class ApiResponseVO<T> {
	private boolean success;
	private String message;
	private T data;
	
	public ApiResponseVO() {
		super();
	}
	public ApiResponseVO(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}
}
