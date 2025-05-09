package com.inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Map<String,Object>> handleProductNotFound(ProductNotFoundException ex)
	{
		return generateErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<Map<String,Object>> handleOrderNotFound(OrderNotFoundException ex)
	{
		return generateErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(SupplierNotFoundException.class)
	public ResponseEntity<Map<String,Object>> handleSupplierNotFound(SupplierNotFoundException ex)
	{
		return generateErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DuplicateProductException.class)
	public ResponseEntity<Map<String,Object>> handleDuplicateProductFound(DuplicateProductException ex)
	{
		return generateErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	private ResponseEntity<Map<String,Object>> generateErrorResponse(String msg,HttpStatus status)
	{
		Map<String,Object> errorBody = new HashMap<>();
		errorBody.put("timestamp", LocalDateTime.now());
		//errorBody.put("status", status.value());
		errorBody.put("error", status.getReasonPhrase());
		errorBody.put("message", msg);
		return new ResponseEntity<>(errorBody,status);
	}
}
