package com.uplan.validation;

public class EntityMessageContainException extends RuntimeException {

  private EntityValidationFailDto errors;

  public EntityMessageContainException(EntityValidationFailDto errors) {
    this.errors = errors;
  }

  public EntityValidationFailDto getErrors() {
    return errors;
  }
}
