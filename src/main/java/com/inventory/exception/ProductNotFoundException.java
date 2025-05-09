package com.inventory.exception;

public class ProductNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2843939360648971284L;

	public ProductNotFoundException(String msg) {
		super(msg);
	}
	
}
