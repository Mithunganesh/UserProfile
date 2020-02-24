package com.rade.api;

import java.io.Serializable;

public class APIResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private int status;
	private String message;
	private Object result;

	public APIResponse(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
