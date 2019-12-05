package com.uplan.validation;

import java.util.List;

public class MessageContainException extends RuntimeException {

  private List<String> errors;

  public MessageContainException(List<String> errors) {
    this.errors = errors;
  }

  public List<String> getErrors() {
    return errors;
  }

}
