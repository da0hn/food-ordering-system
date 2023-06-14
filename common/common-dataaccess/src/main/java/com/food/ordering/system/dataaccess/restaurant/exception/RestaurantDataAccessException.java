package com.food.ordering.system.dataaccess.restaurant.exception;

import java.io.Serial;

public class RestaurantDataAccessException extends RuntimeException {


  @Serial private static final long serialVersionUID = -8055180938839456758L;

  public RestaurantDataAccessException() {
  }

  public RestaurantDataAccessException(final String message) {
    super(message);
  }

  public RestaurantDataAccessException(
    final String message,
    final Throwable cause
  ) {
    super(message, cause);
  }

}
