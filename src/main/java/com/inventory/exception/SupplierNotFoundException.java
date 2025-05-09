package com.inventory.exception;

public class SupplierNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2329892344984428859L;

	public SupplierNotFoundException(String msg) {
		super(msg);
	}
}
