package org.nomoretea.mediaService.web.error;

import org.hibernate.exception.ConstraintViolationException;
import org.nomoretea.mediaService.web.dto.ExceptionDTO;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	public RestResponseEntityExceptionHandler() {
		super();
	}

	// API

	// 400

	@ExceptionHandler({ ConstraintViolationException.class, DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleBadRequest(final RuntimeException ex, final WebRequest request) {
		final String bodyOfResponse = "This should be application specific";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		final String bodyOfResponse = "This should be application specific";
		// ex.getCause() instanceof JsonMappingException, JsonParseException // for additional information later on
		return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		final String bodyOfResponse = "This should be application specific";
		return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
	}

	// 403

	// 404

	@ExceptionHandler(value = { EntityNotFoundException.class })
	protected ResponseEntity<Object> handleBadRequest(final EntityNotFoundException ex, final WebRequest request) {
//		final String bodyOfResponse = "This should be application specific";
		return handleExceptionInternal(ex, new ExceptionDTO("error", "The URI does not match any of the recognised resources, or, if you are asking for a specific resource with an ID, that resource does not exist."), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	// 409

	@ExceptionHandler({ InvalidDataAccessApiUsageException.class, DataAccessException.class })
	protected ResponseEntity<Object> handleConflict(final RuntimeException ex, final WebRequest request) {
		final String bodyOfResponse = "This should be application specific";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	// 412

	// 500

	@ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class })
    /*500*/public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
		logger.error("500 Status Code", ex);
		final String bodyOfResponse = "This should be application specific";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

}
