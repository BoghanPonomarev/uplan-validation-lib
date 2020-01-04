package com.uplan.validation.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uplan.validation.exception.MessageContainException;

public class ListValidationFailedResponseClientHandlerImpl implements ListValidationFailedResponseClientHandler {

  /**
   * Throws {@link MessageContainException} only if json mapping passed successfully.
   */
  @Override
  public void throwIfValidationError(String responseContent) {
    MessageContainException simpleValidationErrorDto = parseJson(responseContent);

    if (simpleValidationErrorDto != null) {
      throw simpleValidationErrorDto;
    }
  }

  /**
   * Pareses validation failed response.
   *
   * @return - list of error messages, if json parsing failed - returns null.
   */
  private MessageContainException parseJson(String content) {
    try {
      ObjectMapper jsonMapper = new ObjectMapper();
      jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      MessageContainException simpleErrorDto = jsonMapper.readValue(content, MessageContainException.class);
      return simpleErrorDto;
    } catch (JsonProcessingException ignored) {
    }
    return null;
  }

}
