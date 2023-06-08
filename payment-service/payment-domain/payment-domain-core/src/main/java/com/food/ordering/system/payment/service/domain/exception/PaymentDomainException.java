package com.food.ordering.system.payment.service.domain.exception;

import com.food.ordering.system.domain.exception.DomainException;

public class PaymentDomainException extends DomainException {


  public PaymentDomainException(final String message) {
    super(message);
  }

  public PaymentDomainException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
