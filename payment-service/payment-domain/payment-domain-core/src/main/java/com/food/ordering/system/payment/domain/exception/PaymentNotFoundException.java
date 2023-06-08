package com.food.ordering.system.payment.domain.exception;

import com.food.ordering.system.domain.exception.DomainException;

import java.io.Serial;

public class PaymentNotFoundException extends DomainException {
  @Serial
  private static final long serialVersionUID = -5157159403861902090L;

  public PaymentNotFoundException(final String message) {
    super(message);
  }

  public PaymentNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
