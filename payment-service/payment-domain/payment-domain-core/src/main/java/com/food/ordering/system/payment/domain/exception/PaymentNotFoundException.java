package com.food.ordering.system.payment.domain.exception;

import com.food.ordering.system.domain.exception.DomainException;

public class PaymentNotFoundException extends DomainException {
  public PaymentNotFoundException(final String message) {
    super(message);
  }

  public PaymentNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
