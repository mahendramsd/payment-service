package com.daofab.service.util;

import org.springframework.http.HttpStatus;

/**
 * @author Mahendra on 5/17/2023
 */
public enum CustomErrorCodes {

	USER_DISABLED(1001, "USER_DISABLED", HttpStatus.BAD_REQUEST),
	INVALID_CREDENTIALS(1002, "INVALID_CREDENTIALS", HttpStatus.BAD_REQUEST),
	USER_NOT_FOUND(1003, "Active User Not Found", HttpStatus.NOT_FOUND),
	INVALID_RECORD(1004, "Cannot Retrieve Payment details, try again later", HttpStatus.INTERNAL_SERVER_ERROR),
	PAYMENT_DETAIL_ERROR(1005, "Cannot Retrieve Payment details by parent, try again later", HttpStatus.INTERNAL_SERVER_ERROR);



    private final int id;
	private final String msg;
	private final HttpStatus httpCode;

	CustomErrorCodes(int id, String msg, HttpStatus httpCode) {
		this.id = id;
		this.msg = msg;
		this.httpCode = httpCode;
	}

	public int getId() {
		return this.id;
	}

	public String getMsg() {
		return this.msg;
	}

	public HttpStatus getHttpCode() {
		return this.httpCode;
	}

}
