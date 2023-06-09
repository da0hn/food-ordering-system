package com.food.ordering.system.payment.service.domain.exception;

import com.food.ordering.system.domain.exception.DomainException;

import java.io.Serial;

public class PaymentApplicationServiceException extends DomainException {
  @Serial
  private static final long serialVersionUID = -2576182554119585372L;

  public PaymentApplicationServiceException(final String message) {
    super(message);
  }

  public PaymentApplicationServiceException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
