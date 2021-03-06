package com.uplan.validation.validator;

import com.uplan.validation.ValidationFailContainer;
import com.uplan.validation.exception.EntityMessageContainException;

import java.util.Optional;

/**
 * Abstract dto validator that allows map error messages on validation dto.
 * Need for validation entities which error response
 * messages should be separated from each other.
 *
 * @param <T>  - entity that will be validated during inheritance.
 * @param <EX> - validation fail dto that will be included to final exception if validation fail.
 */
public abstract class EntityMappedDtoValidator<T, EX extends ValidationFailContainer> {

  /**
   * Method that handle general validation logic around real validator.
   * Can`t be overridden, for this purposes see {@link EntityMappedDtoValidator#validateEntity(Object)}
   *
   * @param targetDto - object that will be validated.
   * @throws EntityMessageContainException - throws when validation reveal incorrect data.
   */
  public final void validate(T targetDto) throws EntityMessageContainException {
    validateWithResult(targetDto)
            .ifPresent((entityValidationResult) -> {
              throw new EntityMessageContainException(entityValidationResult);
            });
  }

  /**
   * Method that validate target dto and return validation result.
   * Unlike {@link DtoValidator#validate(Object)} method does not throw exception
   * in case of validation mistakes.
   *
   * @param targetDto - object that will be validated.
   * @return - validation response entity in  optional, if no validation errors - empty optional.
   */
  public final Optional<EX> validateWithResult(T targetDto) {
    EX entityValidationResult = validateEntity(targetDto);

    if (entityValidationResult != null && entityValidationResult.isContainErrorCodes()) {
      return Optional.of(entityValidationResult);
    }
    return Optional.empty();
  }

  /**
   * Method that should be overridden with real validation, all possible
   * validation messages should be included to pre-created  list. It is considered that
   * object that inherits {@link ValidationFailContainer}.if result entity is null or if
   * <EX extends EntityValidationFailDto> result object returns false on
   * {@link ValidationFailContainer#isContainErrorCodes()} call - validation pass successfully.
   *
   * @param targetDto - object that will be validated.
   * @return - dto object that may contain separated error validation messages.
   */
  protected abstract EX validateEntity(T targetDto);

}
