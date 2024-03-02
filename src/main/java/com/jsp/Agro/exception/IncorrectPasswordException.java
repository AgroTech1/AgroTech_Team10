package com.jsp.Agro.exception;

public class IncorrectPasswordException extends RuntimeException {
	private String message="Password is Incorrect";

	public String getMessage() {
		return message;
	}

	public IncorrectPasswordException(String message) {
		super();
		this.message = message;
	}

	public IncorrectPasswordException() {
		super();
	}
}


