package com.worldoffice.test.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;

/**
 * 
 * @author srcortes
 *
 */
@Data
public class ManagerApiException extends Exception {
	private HttpStatus status;	
	private static final long serialVersionUID = 1L;
	public ManagerApiException(HttpStatus status, String message, Throwable cause) {
		super(message, cause);
		this.status = status;
	}
	public ManagerApiException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}	
}
