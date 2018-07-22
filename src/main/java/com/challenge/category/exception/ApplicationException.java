package com.challenge.category.exception;

public class ApplicationException extends RuntimeException {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 4799244552345262596L;
	
	public ApplicationException(String message,Exception exception) {
		super(message,exception);
	}

}
