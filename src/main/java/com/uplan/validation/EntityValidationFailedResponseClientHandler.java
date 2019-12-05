package com.uplan.validation;

/**
 * APi that holds common logic for client validation failed response handling.
 * @param <T> - dto that fits the response of server which was contacted.
 */
public interface EntityValidationFailedResponseClientHandler<T extends EntityValidationFailDto> {

    /**
     * Method that should throw {@link EntityMessageContainException} if another server validation failed.
     * It is important that before calling this method check http status of response and only if
     * it corresponds to the code defined by the contract, as an validation failed code.
     * @param responseContent - response content that may contain validation exception info.
     * @param validationClass - type of dto that contains validation information defined in the contract
     * of interaction between servers.
     */
    void throwIfValidationError(String responseContent, Class<T> validationClass);

}
