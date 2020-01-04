package com.uplan.validation.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uplan.validation.exception.MessageContainException;
import com.uplan.validation.exception.SimpleValidationErrorDto;

public class SimpleValidationFailedResponseClientHandlerImpl implements ListValidationFailedResponseClientHandler {

  /**
   * Throws {@link MessageContainException} only if json mapping passed successfully.
   */
  @Override
  public void throwIfValidationError(String responseContent) {
    SimpleValidationErrorDto simpleValidationErrorDto = parseJson(responseContent);

    if (simpleValidationErrorDto != null) {
      throw new MessageContainException(simpleValidationErrorDto);
    }
  }

  /**
   * Pareses validation failed response.
   *
   * @return - list of error messages, if json parsing failed - returns null.
   */
  private SimpleValidationErrorDto parseJson(String content) {
    try {
      ObjectMapper jsonMapper = new ObjectMapper();
      jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      return jsonMapper.readValue(content, SimpleValidationErrorDto.class);
    } catch (JsonProcessingException ignored) {
    }
    return null;
  }

}
