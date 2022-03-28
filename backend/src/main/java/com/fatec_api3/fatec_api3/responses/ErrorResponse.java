package com.fatec_api3.fatec_api3.responses;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ErrorResponse {
  private Integer statusCode;
  private Boolean success;
  private String error;

  public ErrorResponse(String error, HttpStatus statusCode) {
    this.statusCode = statusCode.value();
    this.success = false;
    this.error = error;
  }
}
