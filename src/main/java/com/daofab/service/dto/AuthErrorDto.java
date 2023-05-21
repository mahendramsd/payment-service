package com.daofab.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Mahendra on 5/17/2023
 */
public class AuthErrorDto {
  @JsonProperty("error_code")
  private int errorCode;
  @JsonProperty("error_message")
  private String errorMessage;


  public AuthErrorDto(int errorCode, String errorMessage) {
    super();
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

}
