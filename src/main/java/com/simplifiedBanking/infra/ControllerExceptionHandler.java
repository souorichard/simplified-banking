package com.simplifiedBanking.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.simplifiedBanking.dtos.ExceptionResponse;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;

@RestControllerAdvice
public class ControllerExceptionHandler {
  
  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ExceptionResponse> threatDuplicateEntry(DataIntegrityViolationException exception) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(400, "User already registered");

    return ResponseEntity.badRequest().body(exceptionResponse);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ExceptionResponse> threat404(EntityNotFoundException exception) {
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionResponse> threatGeneralException(EntityNotFoundException exception) {
    ExceptionResponse exceptionResponse = new ExceptionResponse(500, exception.getMessage());

    return ResponseEntity.internalServerError().body(exceptionResponse);
  }
}
