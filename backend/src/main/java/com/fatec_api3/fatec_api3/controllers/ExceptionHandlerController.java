package com.fatec_api3.fatec_api3.controllers;

import com.fatec_api3.fatec_api3.exceptions.ResourceAlreadyExistsException;
import com.fatec_api3.fatec_api3.exceptions.ResourceNotFoundException;
import com.fatec_api3.fatec_api3.responses.ErrorResponse;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;

public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
    ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    return new ResponseEntity(error, HttpStatus.valueOf(error.getStatusCode()));
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public final ResponseEntity<Object> handleDataIntegrityViolationException(
      DataIntegrityViolationException ex, WebRequest request) {
    ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
    return new ResponseEntity(error, HttpStatus.valueOf(error.getStatusCode()));
  }

  @ExceptionHandler(ResourceAlreadyExistsException.class)
  public final ResponseEntity<Object> handleResourceAlreadyExistsException(
      ResourceAlreadyExistsException ex, WebRequest request) {
    ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage(), HttpStatus.CONFLICT);
    return new ResponseEntity(error, HttpStatus.valueOf(error.getStatusCode()));
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public final ResponseEntity<Object> handleResourceNotFoundException(
      ResourceNotFoundException ex, WebRequest request) {
    ErrorResponse error = new ErrorResponse(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    return new ResponseEntity(error, HttpStatus.valueOf(error.getStatusCode()));
  }

  @ExceptionHandler(ExpiredJwtException.class)
  public final ResponseEntity<Object> handleExpiredJwtException(
      ExpiredJwtException ex, WebRequest request) {
    ErrorResponse error = new ErrorResponse("Your token has expired", HttpStatus.NOT_FOUND);
    return new ResponseEntity(error, HttpStatus.valueOf(error.getStatusCode()));
  }

}
