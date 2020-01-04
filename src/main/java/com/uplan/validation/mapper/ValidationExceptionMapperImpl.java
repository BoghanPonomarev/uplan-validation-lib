package com.uplan.validation.mapper;

import com.uplan.validation.ValidationFailContainer;
import com.uplan.validation.exception.MessageContainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ValidationExceptionMapperImpl implements ValidationExceptionMapper {

  @Override
  public ResponseEntity<MessageContainException> handleErrors(HttpStatus errorResponseStatus, MessageContainException simpleErrorDto) {
    if (!simpleErrorDto.getErrorCodes().isEmpty()) {
      return ResponseEntity.status(errorResponseStatus).body(simpleErrorDto);
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
