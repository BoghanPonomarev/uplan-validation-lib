package com.uplan.validation;

/**
 * Dto from which should extend all classes that represent final information
 * about the validation result.
 */
public abstract class EntityValidationFailDto {

  /**
   * Method that describes whether the validation was successful
   * or the object has errors.
   * @return - true if validation failed and dto contains validation error messages, otherwise - false.
   */
  public abstract boolean isContainErrorCodes();

}
