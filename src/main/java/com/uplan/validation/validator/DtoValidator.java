package com.uplan.validation.validator;

import com.uplan.validation.exception.MessageContainException;
import com.uplan.validation.exception.SimpleValidationErrorDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract simple dto validator. Need for validation entities which error response
 * messages can be fit to plane list of strings.
 *
 * @param <T> - entity that will be validated during inheritance.
 */
public abstract class DtoValidator<T> {

  /**
   * Method that handle general validation logic around real validator.
   * Can`t be overridden, for this purposes see {@link DtoValidator#validate(Object, List)}
   *
   * @param targetDto - object that will be validated.
   * @throws MessageContainException - throws when validation reveal incorrect data.
   */
  public final void validate(T targetDto) throws MessageContainException {
    List<String> errorCodes = validateWithResult(targetDto);
    if (!errorCodes.isEmpty()) {
      SimpleValidationErrorDto simpleValidationErrorDto = new SimpleValidationErrorDto(errorCodes);
      throw new MessageContainException(simpleValidationErrorDto);
    }
  }

  /**
   * Method that validate target dto and return validation result.
   * Unlike {@link DtoValidator#validate(Object)} method does not throw exception
   * in case of validation mistakes.
   *
   * @param targetDto - object that will be validated.
   * @return - list of error codes if some validation errors has occur and
   * empty list if not.
   */
  public final List<String> validateWithResult(T targetDto) {
    List<String> errorCodes = new ArrayList<>();
    validate(targetDto, errorCodes);
    return errorCodes;
  }

  /**
   * Method that should be overridden with real validation, all possible
   * validation messages should be included to errorCodes list. It is considered that
   * if no error messages were added to the list, the validation was successful.
   *
   * @param targetDto  - object that will be validated.
   * @param errorCodes - list for validation error messages including.
   */
  protected abstract void validate(T targetDto, List<String> errorCodes);


}
