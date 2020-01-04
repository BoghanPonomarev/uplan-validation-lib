package com.uplan.validation.mapper;

import com.uplan.validation.ValidationFailContainer;
import com.uplan.validation.exception.MessageContainException;
import com.uplan.validation.exception.SimpleValidationErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * API that covers mapping validation fail info to {@link ResponseEntity}
 */
public interface ValidationExceptionMapper {

  ResponseEntity<SimpleValidationErrorDto> handleErrors(HttpStatus errorResponseStatus, MessageContainException simpleErrorDto);

  <T extends ValidationFailContainer> ResponseEntity<T> handleEntityMappedErrors(HttpStatus errorResponseStatus, T errorCodesEntity);

}
