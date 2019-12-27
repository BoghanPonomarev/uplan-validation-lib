package com.uplan.validation.exception;

import java.util.List;

/**
 * Exception that needed for validation response with single string list messages.
 */
public class MessageContainException extends RuntimeException {

  /**
   * List of error messages cause of which validation failed.
   */
  private List<String> errors;

  public MessageContainException(List<String> errors) {
    this.errors = errors;
  }

  public List<String> getErrors() {
    return errors;
  }

}
