package com.jpm.supersimple.exceptions;

@SuppressWarnings("serial")
public class ValidationException extends ApplicationException {

	public ValidationException(String errorMessage) {
		super(errorMessage);
	}

}
