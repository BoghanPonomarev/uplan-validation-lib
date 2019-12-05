package com.uplan.validation;

public class EntityMessageContainException extends RuntimeException {

  private EntityValidationDto errors;

  public EntityMessageContainException(EntityValidationDto errors) {
    this.errors = errors;
  }

  public EntityValidationDto getErrors() {
    return errors;
  }
}
