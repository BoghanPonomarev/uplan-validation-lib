package com.uplan.validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Interface that fills ResponseDto data by localized validation messages and sets up
 * http status code.
 */
public interface UserValidationExceptionMapper {

    /**
     * Builds error client_response by constructing it from list pf error codes
     * that could contain validation errors and error client_response status
     * which is necessary for each rest client_response.
     *
     * @param errorResponseStatus - http error client_response status.
     * @param errorCodes          - list of error codes.
     * @return - error client_response entity which can be mapped to json, xml etc.
     */
    ResponseEntity<List<String>> handleErrors(HttpStatus errorResponseStatus, List<String> errorCodes);

    <T extends EntityValidationFailDto> ResponseEntity<T> handleEntityMappedErrors(HttpStatus errorResponseStatus, T errorCodesEntity);

}
