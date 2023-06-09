package com.food.ordering.system.restaurant.service.domain.exception;

import com.food.ordering.system.domain.exception.DomainException;

public class RestaurantDomainException extends DomainException {
  public RestaurantDomainException(final String message) {
    super(message);
  }

  public RestaurantDomainException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
