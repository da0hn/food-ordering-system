package com.food.ordering.system.domain.exception;

import java.text.MessageFormat;

public class OrderDomainException extends DomainException {

  public OrderDomainException(final String message) {
    super(message);
  }

  public OrderDomainException(
    final String message,
    final Object... args
  ) {
    super(MessageFormat.format(message, args));
  }

  public OrderDomainException(
    final String message,
    final Throwable cause
  ) {
    super(message, cause);
  }

}
