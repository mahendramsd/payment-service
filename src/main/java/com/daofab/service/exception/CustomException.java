package com.daofab.service.exception;


import com.daofab.service.util.CustomErrorCodes;

/**
 * @author Mahendra on 5/17/2023
 */
public class CustomException extends RuntimeException {

  private CustomErrorCodes customErrorCodes;

  public CustomException(String message) {
    super(message);
  }


  public CustomException(String message, Throwable cause) {
    super(message, cause);
  }

  public CustomException(CustomErrorCodes customErrorCodes) {
    this.customErrorCodes = customErrorCodes;
  }

  public CustomErrorCodes getCustomErrorCodes() {
    return customErrorCodes;
  }

}
