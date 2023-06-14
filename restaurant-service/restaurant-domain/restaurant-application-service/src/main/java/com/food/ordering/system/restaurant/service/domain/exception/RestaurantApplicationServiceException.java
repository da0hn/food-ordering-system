package com.food.ordering.system.restaurant.service.domain.exception;

import com.food.ordering.system.domain.exception.DomainException;

public class RestaurantApplicationServiceException extends DomainException {
  public RestaurantApplicationServiceException(final String message) {
    super(message);
  }

  public RestaurantApplicationServiceException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
