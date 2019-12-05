package com.uplan.validation;

import java.util.ArrayList;
import java.util.List;

public abstract class DtoValidator<T> {

    public final void validate(T targetDto) throws MessageContainException {
        List<String> errorCodes = new ArrayList<>();
        validate(targetDto, errorCodes);
        if (!errorCodes.isEmpty()) {
            throw new MessageContainException(errorCodes);
        }
    }

    protected abstract void validate(T targetDto, List<String> errorCodes);

}
