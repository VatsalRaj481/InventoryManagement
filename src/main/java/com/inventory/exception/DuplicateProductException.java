package com.inventory.exception;

public class DuplicateProductException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8794441975291484015L;

	public DuplicateProductException(String msg)
	{
		super(msg);
	}
}
