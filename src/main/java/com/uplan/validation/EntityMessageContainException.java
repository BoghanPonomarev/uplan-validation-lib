package com.uplan.validation;

/**
 * Exception that needed for validation response with separated messages.
 */
public class EntityMessageContainException extends RuntimeException {

  /**
   * Dto object that contains fields for validation fails info.
   */
  private EntityValidationFailDto errors;

  public EntityMessageContainException(EntityValidationFailDto errors) {
    this.errors = errors;
  }

  public EntityValidationFailDto getErrors() {
    return errors;
  }

}
