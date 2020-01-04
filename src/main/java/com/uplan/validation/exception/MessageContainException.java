package com.uplan.validation.exception;

/**
 * Exception that needed for validation response with single string list messages.
 */
public class MessageContainException extends RuntimeException {

  /**
   * Dto with error messages cause of which validation failed.
   */
  private SimpleValidationErrorDto simpleValidationErrorDto;

  public MessageContainException(SimpleValidationErrorDto simpleValidationErrorDto) {
    this.simpleValidationErrorDto = simpleValidationErrorDto;
  }

  public SimpleValidationErrorDto getSimpleValidationErrorDto() {
    return simpleValidationErrorDto;
  }

  public void setSimpleValidationErrorDto(SimpleValidationErrorDto simpleValidationErrorDto) {
    this.simpleValidationErrorDto = simpleValidationErrorDto;
  }

}
