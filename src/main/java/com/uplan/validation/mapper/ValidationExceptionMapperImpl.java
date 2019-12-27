package com.uplan.validation.mapper;

import com.uplan.validation.ValidationFailContainer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ValidationExceptionMapperImpl implements ValidationExceptionMapper {

  @Override
  public ResponseEntity<List<String>> handleErrors(HttpStatus errorResponseStatus, List<String> errorCodes) {
    if (!errorCodes.isEmpty()) {
      return ResponseEntity.status(errorResponseStatus).body(errorCodes);
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
