package com.uplan.validation;

/**
 * Dto interface from which should implement all classes that represent final information
 * about the validation result.
 */
public interface ValidationFailContainer {

  /**
   * Method that describes whether the validation was successful
   * or the object has errors.
   *
   * @return - true if validation failed and dto contains validation error messages, otherwise - false.
   */
  boolean isContainErrorCodes();

}
