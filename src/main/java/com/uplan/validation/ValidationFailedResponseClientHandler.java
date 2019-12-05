package com.uplan.validation;

public interface ValidationFailedResponseClientHandler<T extends EntityValidationDto> {

    void throwIfValidationError(String responseContent, Class<T> validationClass);

}
