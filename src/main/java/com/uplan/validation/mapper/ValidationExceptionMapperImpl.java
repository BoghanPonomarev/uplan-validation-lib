package com.uplan.validation.mapper;

import com.uplan.validation.ValidationFailContainer;
import com.uplan.validation.exception.MessageContainException;
import com.uplan.validation.exception.SimpleValidationErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ValidationExceptionMapperImpl implements ValidationExceptionMapper {

  @Override
  public ResponseEntity<SimpleValidationErrorDto> handleErrors(HttpStatus errorResponseStatus, MessageContainException simpleErrorDto) {
    SimpleValidationErrorDto simpleValidationErrorDto = simpleErrorDto.getSimpleValidationErrorDto();
    if (simpleValidationErrorDto != null && !simpleValidationErrorDto.getErrorCodes().isEmpty()) {
      return ResponseEntity.status(errorResponseStatus).body(simpleValidationErrorDto);
    }
    return null;
  }

  @Override
  public <T extends ValidationFailContainer> ResponseEntity<T> handleEntityMappedErrors(HttpStatus errorResponseStatus, T errorCodesEntity) {
    if (errorCodesEntity.isContainErrorCodes()) {
      return ResponseEntity.status(errorResponseStatus).body(errorCodesEntity);
    }
    return null;
  }

}
