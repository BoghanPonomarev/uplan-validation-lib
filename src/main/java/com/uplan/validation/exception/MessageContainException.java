package com.uplan.validation.exception;

import java.util.List;

/**
 * Exception that needed for validation response with single string list messages.
 */
public class MessageContainException extends RuntimeException {

  /**
   * List of error messages cause of which validation failed.
   */
  private List<String> errorCodes;

  public MessageContainException(List<String> errorCodes) {
    this.errorCodes = errorCodes;
  }

  public List<String> getErrorCodes() {
    return errorCodes;
  }

}
