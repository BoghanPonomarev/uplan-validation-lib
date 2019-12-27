package com.uplan.validation.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uplan.validation.ValidationFailContainer;
import com.uplan.validation.exception.EntityMessageContainException;

public class EntityValidationFailedResponseClientHandlerImpl<T extends ValidationFailContainer> implements EntityValidationFailedResponseClientHandler<T> {

  /**
   * Throws {@link EntityMessageContainException} only if json mapping passed successfully.
   */
  @Override
  public void throwIfValidationError(String responseContent, Class<T> validationClass) {
    T validationFiledResponseDto = parseJson(responseContent, validationClass);

    if (validationFiledResponseDto != null) {
      throw new EntityMessageContainException(validationFiledResponseDto);
    }
  }

  /**
   * Pareses validation failed response. Notice that if there are unknown
   * fields in response - it will not fail.
   *
   * @return - dto object which was filled by server validation response.
   * If server validation response and expected validation filed dto are not equals - returns null.
   */
  private T parseJson(String content, Class<T> parsingType) {
    try {
      ObjectMapper jsonMapper = new ObjectMapper();
      jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      return jsonMapper.readValue(content, parsingType);
    } catch (JsonProcessingException ignored) {
    }
    return null;
  }

}
