package com.food.ordering.system.payment.domain.exception;

import com.food.ordering.system.domain.exception.DomainException;

import java.io.Serial;

public class PaymentDomainException extends DomainException {

  @Serial
  private static final long serialVersionUID = 4682770145257011186L;

  public PaymentDomainException(final String message) {
    super(message);
  }

  public PaymentDomainException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
