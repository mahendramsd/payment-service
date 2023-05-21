package com.daofab.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Mahendra on 5/17/2023
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

  private static final long serialVersionUID = 1L;

  public ResourceNotFoundException(String message) {

    super(message);
  }
}
