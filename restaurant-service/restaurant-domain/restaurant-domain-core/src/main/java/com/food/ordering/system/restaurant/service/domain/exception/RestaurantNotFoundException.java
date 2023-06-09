package com.food.ordering.system.restaurant.service.domain.exception;

import com.food.ordering.system.domain.exception.DomainException;

public class RestaurantNotFoundException extends DomainException {
  public RestaurantNotFoundException(final String message) {
    super(message);
  }

  public RestaurantNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
