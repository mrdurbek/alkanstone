package com.alkan.AlkanStone.payload;

public class ApiResponse {
	
	private String message;
	private boolean response;
	
	public ApiResponse() {}
	
	public ApiResponse(String message, boolean response) {
		super();
		this.message = message;
		this.response = response;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isResponse() {
		return response;
	}

	public void setResponse(boolean response) {
		this.response = response;
	}
	
	
}
