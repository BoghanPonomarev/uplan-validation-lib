package com.uplan.validation.handler;

import com.uplan.validation.exception.MessageContainException;

/**
 * API that holds common logic for client validation failed response handling.
 * Use only in case validation returns array of strings.
 */
public interface ListValidationFailedResponseClientHandler {

  /**
   * Accepts response content and throws {@link MessageContainException} if
   * validation error messages array corresponds to the expected form.
   * It is important that before calling this method check http status of response and only if
   * it corresponds to the code defined by the contract, as an validation failed code.
   *
   * @param responseContent - response content that may contain validation exception info.
   */
  void throwIfValidationError(String responseContent);

}
