package com.uplan.validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;


/**
 * Spring validation exception handler that intercepts {@link MessageContainException} ,
 * {@link EntityMessageContainException}, fill it with mapper and add failed validation http status.
 */
@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

  private ValidationExceptionMapper validationExceptionMapper;

  /**
   * Status of final response when validation failed. Can be established.
   */
  private HttpStatus failedValidationStatus;

  public ValidationExceptionHandler() {
    validationExceptionMapper = new ValidationExceptionMapperImpl();
    failedValidationStatus = HttpStatus.BAD_REQUEST;
  }

  @ResponseBody
  @ExceptionHandler({MessageContainException.class})
  public ResponseEntity<List<String>> handleException(MessageContainException exception) {
    return validationExceptionMapper.handleErrors(failedValidationStatus,  exception.getErrors());
  }

  @ResponseBody
  @ExceptionHandler({EntityMessageContainException.class})
  public  ResponseEntity<? extends EntityValidationFailDto> handleException(EntityMessageContainException exception) {
    return validationExceptionMapper.handleEntityMappedErrors(failedValidationStatus,  exception.getErrors());
  }

  public void setFailedValidationStatus(HttpStatus failedValidationStatus) {
    this.failedValidationStatus = failedValidationStatus;
  }

  public void setValidationExceptionMapper(ValidationExceptionMapper validationExceptionMapper) {
    this.validationExceptionMapper = validationExceptionMapper;
  }

}
