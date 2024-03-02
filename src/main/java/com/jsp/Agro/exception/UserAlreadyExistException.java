package com.jsp.Agro.exception;

public class UserAlreadyExistException extends RuntimeException {
	private String message="Email id already exist";

	public String getMessage() {
		return message;
	}

	public UserAlreadyExistException(String message) {
		super();
		this.message = message;
	}

	public UserAlreadyExistException() {
		super();
	}
	
	
}


