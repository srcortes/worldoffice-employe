package com.worldoffice.test.response;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * @author srcortes
 *
 */
@Data
public class ManagerApiResponse<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private String status;
	private String code;
	private String message;
	private T dataInformation;	
	public ManagerApiResponse(String status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}	
	public ManagerApiResponse(String status, String code, String message, T dataInformation) {
		this.status = status;
		this.code = code;
		this.message = message;
		this.dataInformation = dataInformation;
	}
}
