package com.food.ordering.system.domain.exception;

public class OrderNotFoundException extends OrderDomainException {

  public OrderNotFoundException(final String message) {
    super(message);
  }

  public OrderNotFoundException(
    final String message,
    final Object... args
  ) {
    super(message, args);
  }

  public OrderNotFoundException(
    final String message,
    final Throwable cause
  ) {
    super(message, cause);
  }

}
