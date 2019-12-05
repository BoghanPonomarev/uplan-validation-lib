package com.uplan.validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;


@ControllerAdvice
public class SystemExceptionHandler extends ResponseEntityExceptionHandler {

  private final UserValidationExceptionMapper userValidationExceptionMapper;

  public SystemExceptionHandler() {
    userValidationExceptionMapper = new UserValidationExceptionMapperImpl();
  }

  public SystemExceptionHandler(UserValidationExceptionMapper userValidationExceptionMapper) {
    this.userValidationExceptionMapper = userValidationExceptionMapper;
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

}
