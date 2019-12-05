package com.uplan.validation;

public interface ValidationFailedResponseClientHandler<T extends EntityValidationFailDto> {

    void throwIfValidationError(String responseContent, Class<T> validationClass);

}
