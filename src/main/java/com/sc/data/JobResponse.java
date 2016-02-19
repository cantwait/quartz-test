package com.sc.data;

import java.io.Serializable;

public class JobResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	private int status;
	private String message;
	
	public JobResponse(){}
	
	public JobResponse(int status, String message){
		this.status = status;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	

}
