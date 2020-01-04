package com.uplan.validation.exception;

import java.util.List;

public class SimpleValidationErrorDto {

  private List<String> errorCodes;

  public SimpleValidationErrorDto(List<String> errorCodes) {
    this.errorCodes = errorCodes;
  }

  public List<String> getErrorCodes() {
    return errorCodes;
  }

  public void setErrorCodes(List<String> errorCodes) {
    this.errorCodes = errorCodes;
  }

}
