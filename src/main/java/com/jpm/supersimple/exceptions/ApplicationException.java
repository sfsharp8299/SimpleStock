package com.jpm.supersimple.exceptions;

@SuppressWarnings("serial")
public abstract class ApplicationException extends Exception {

	ApplicationException(String message) {
		super(message);
	}
	
}
