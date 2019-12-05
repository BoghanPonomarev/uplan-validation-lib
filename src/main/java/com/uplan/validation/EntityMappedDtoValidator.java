package com.uplan.validation;

public abstract class EntityMappedDtoValidator<T,EX extends EntityValidationDto> {

  public final void validate(T targetDto) throws EntityMessageContainException {
    EX entityValidationResult = validateEntity(targetDto);
    if (entityValidationResult.isContainErrorCodes()) {
      throw new EntityMessageContainException(entityValidationResult);
    }
  }

  protected abstract EX validateEntity(T targetDto);

}
