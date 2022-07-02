package com.numpyninja.lms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class DuplicateResourceFound extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
public DuplicateResourceFound(String message) {
	super(message);
}
}
