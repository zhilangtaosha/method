package com.method.userservice.controllers.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
	protected String handleException(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "There was a problem handling the request," + ex.getMessage();
		return bodyOfResponse;
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleOtherExceptions(final Exception ex, final WebRequest req) {
		return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, req);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		headers.add("error", ex.getMessage());
		return super.handleMissingServletRequestParameter(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		headers.add("error", ex.getMessage());
		body = (String) ex.getMessage();
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
}
