package com.jpm.supersimple.exceptions;

@SuppressWarnings("serial")
public class InstrumentNotFoundException extends ApplicationException {
	
    public InstrumentNotFoundException(String message) {
        super(message);
    }
    
}