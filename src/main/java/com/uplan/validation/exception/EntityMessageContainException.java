package com.uplan.validation.exception;

import com.uplan.validation.ValidationFailContainer;

/**
 * Exception that needed for validation response with separated messages.
 */
public class EntityMessageContainException extends RuntimeException {

  /**
   * Dto object that contains fields for validation fails info.
   */
  private ValidationFailContainer errors;

  public EntityMessageContainException(ValidationFailContainer errors) {
    this.errors = errors;
  }

  public ValidationFailContainer getErrors() {
    return errors;
  }

}
