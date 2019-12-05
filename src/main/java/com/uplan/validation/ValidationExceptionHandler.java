package com.uplan.validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;


@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

  private UserValidationExceptionMapper userValidationExceptionMapper;

  public ValidationExceptionHandler() {
    userValidationExceptionMapper = new UserValidationExceptionMapperImpl();
  }

  @ResponseBody
  @ExceptionHandler({MessageContainException.class})
  public ResponseEntity<List<String>> handleException(MessageContainException exception) {
    return userValidationExceptionMapper.handleErrors(HttpStatus.BAD_REQUEST,  exception.getErrors());
  }

  @ResponseBody
  @ExceptionHandler({EntityMessageContainException.class})
  public  ResponseEntity<? extends EntityValidationFailDto> handleException(EntityMessageContainException exception) {
    return userValidationExceptionMapper.handleEntityMappedErrors(HttpStatus.BAD_REQUEST,  exception.getErrors());
  }

  public void setUserValidationExceptionMapper(UserValidationExceptionMapper userValidationExceptionMapper) {
    this.userValidationExceptionMapper = userValidationExceptionMapper;
  }

}