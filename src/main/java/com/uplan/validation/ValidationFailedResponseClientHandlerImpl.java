package com.uplan.validation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ValidationFailedResponseClientHandlerImpl<T extends EntityValidationDto> implements ValidationFailedResponseClientHandler<T> {

    @Override
    public void throwIfValidationError(String responseContent, Class<T> validationClass) {
        T userRegistrationValidationDto = parseJson(responseContent, validationClass);
        throw new EntityMessageContainException(userRegistrationValidationDto);
    }

    private T parseJson(String content, Class<T> parsingType) {
        try {
            ObjectMapper jsonMapper =  new ObjectMapper();
            jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return jsonMapper.readValue(content, parsingType);
        } catch (JsonProcessingException ignored) {
        }
        return null;
    }

}
