package com.examle.democicd.exception;

public class PersonNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7325576999569212140L;

	public PersonNotFoundException(String message) {
		super(message);
	}
}
