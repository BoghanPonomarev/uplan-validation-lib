package com.uplan.validation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

public class ListValidationFailedResponseClientHandlerImpl implements ListValidationFailedResponseClientHandler {

  /**
   * Throws {@link MessageContainException} only if json mapping passed successfully.
   */
  @Override
  public void throwIfValidationError(String responseContent) {
    List<String> validationFailedErrorMessages = parseJson(responseContent);

    if (validationFailedErrorMessages != null) {
      throw new MessageContainException(validationFailedErrorMessages);
    }
  }

  /**
   * Pareses validation failed response.
   * @return - list of error messages, if json parsing failed - returns null.
   */
  private List<String> parseJson(String content) {
    try {
      ObjectMapper jsonMapper =  new ObjectMapper();
      jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      String[] validationErrorMessages = jsonMapper.readValue(content, String[].class);
      return Arrays.asList(validationErrorMessages);
    } catch (JsonProcessingException ignored) {
    }
    return null;
  }

}
