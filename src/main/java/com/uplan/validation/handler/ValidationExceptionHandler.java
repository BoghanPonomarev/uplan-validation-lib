package com.uplan.validation.handler;

import com.uplan.validation.ValidationFailContainer;
import com.uplan.validation.exception.EntityMessageContainException;
import com.uplan.validation.exception.MessageContainException;
import com.uplan.validation.exception.SimpleValidationErrorDto;
import com.uplan.validation.mapper.ValidationExceptionMapper;
import com.uplan.validation.mapper.ValidationExceptionMapperImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


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
  public ResponseEntity<SimpleValidationErrorDto> handleException(MessageContainException exception) {
    return validationExceptionMapper.handleErrors(failedValidationStatus, exception);
  }

  @ResponseBody
  @ExceptionHandler({EntityMessageContainException.class})
  public ResponseEntity<? extends ValidationFailContainer> handleException(EntityMessageContainException exception) {
    return validationExceptionMapper.handleEntityMappedErrors(failedValidationStatus, exception.getErrorDto());
  }

  public void setFailedValidationStatus(HttpStatus failedValidationStatus) {
    this.failedValidationStatus = failedValidationStatus;
  }

  public void setValidationExceptionMapper(ValidationExceptionMapper validationExceptionMapper) {
    this.validationExceptionMapper = validationExceptionMapper;
  }

}
