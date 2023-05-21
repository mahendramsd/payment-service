package com.daofab.service.exception;

import com.daofab.service.dto.AuthErrorDto;
import com.daofab.service.dto.ResponseDto;
import com.daofab.service.util.CustomErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * @author Mahendra on 5/17/2023
 */
@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<?> resourceNotFoundException(Exception ex, WebRequest request) {
    ResponseDto responseDto = ResponseDto.failure(ex.getMessage());
    return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> globalExceptionHandler(Exception ex) {
    ResponseDto responseDto = ResponseDto.failure(ex.getMessage());
    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<?> globalRuntimeExceptionHandler(Exception ex) {
    ResponseDto responseDto = ResponseDto.failure(ex.getMessage());
    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }

  @ExceptionHandler(CustomException.class)
  public ResponseEntity<?> customExceptionHandler(CustomException customException) {
    CustomErrorCodes customErrorCodes = customException.getCustomErrorCodes();
    AuthErrorDto authErrorDto =
            new AuthErrorDto(customErrorCodes.getId(), customErrorCodes.getMsg());
    return new ResponseEntity<>(authErrorDto, customErrorCodes.getHttpCode());
  }


}

