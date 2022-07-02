package com.numpyninja.lms.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DuplicateResourceFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String resourceName;
	String fieldName;
	String fieldValue;

	public DuplicateResourceFoundException(String resourceName, String fieldName, String fieldValue){
        super(String.format("%s already exists with given %s : %s ",resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
	
	public DuplicateResourceFoundException(String message){
		super(message);
    }

}
