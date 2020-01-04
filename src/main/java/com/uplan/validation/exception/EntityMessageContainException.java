package com.uplan.validation.exception;

import com.uplan.validation.ValidationFailContainer;

/**
 * Exception that needed for validation response with separated messages.
 */
public class EntityMessageContainException extends RuntimeException {

  /**
   * Dto object that contains fields for validation fails info.
   */
  private ValidationFailContainer errorDto;

  public EntityMessageContainException(ValidationFailContainer errorDto) {
    this.errorDto = errorDto;
  }

  public ValidationFailContainer getErrorDto() {
    return errorDto;
  }

}
