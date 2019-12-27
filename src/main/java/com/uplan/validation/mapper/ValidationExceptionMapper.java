package com.uplan.validation.mapper;

import com.uplan.validation.ValidationFailContainer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * API that covers mapping validation fail info to {@link ResponseEntity}
 */
public interface ValidationExceptionMapper {

  ResponseEntity<List<String>> handleErrors(HttpStatus errorResponseStatus, List<String> errorCodes);

  <T extends ValidationFailContainer> ResponseEntity<T> handleEntityMappedErrors(HttpStatus errorResponseStatus, T errorCodesEntity);

}
