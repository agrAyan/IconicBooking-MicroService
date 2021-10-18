package com.iconicsbooking.exception;

import java.time.LocalDateTime;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.iconicsbooking.model.ApiErrors;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		//return super.handleMissingServletRequestParameter(ex, headers, status, request);
	
		String message= ex.getMessage();
		LocalDateTime timestamp= LocalDateTime.now();
		String error="missing servlet request parameter ";
		ApiErrors errors= new ApiErrors(timestamp, message, status.value(), error);
		
		return ResponseEntity.status(status).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		String message= ex.getMessage();
		LocalDateTime timestamp= LocalDateTime.now();
		String error="missing path varables";
		ApiErrors errors= new ApiErrors(timestamp, message, status.value(), error);
		
		return ResponseEntity.status(status).body(errors);
				}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String message= ex.getMessage();
		LocalDateTime timestamp= LocalDateTime.now();
		String error="method not supported";
		ApiErrors errors= new ApiErrors(timestamp, message, status.value(), error);
		
		return ResponseEntity.status(status).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message= ex.getMessage();
		LocalDateTime timestamp= LocalDateTime.now();
		String error="media not supported";
		ApiErrors errors= new ApiErrors(timestamp, message, status.value(), error);
		
		return ResponseEntity.status(status.value()).body(errors);
		
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		String message= ex.getMessage();
		LocalDateTime timestamp= LocalDateTime.now();
		String error="Type mismatch exception";
		ApiErrors errors= new ApiErrors(timestamp, message, status.value(), error);
		
		return ResponseEntity.status(status).body(errors);
		
	}
	
	

	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleException(Exception ex) {

		String message= ex.getMessage();
		LocalDateTime timestamp= LocalDateTime.now();
		String error="Event microservice execption";
		ApiErrors errors= new ApiErrors(timestamp, message, HttpStatus.BAD_GATEWAY.value(), error);
		
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(errors);
		
	}
	
}
