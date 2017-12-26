package com.dhcc.zhyl.splider.entity;

import java.io.Serializable;

public class Response implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3226788524139197454L;
	private String errorMsg;
	private String errorCode;

	public Response(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
