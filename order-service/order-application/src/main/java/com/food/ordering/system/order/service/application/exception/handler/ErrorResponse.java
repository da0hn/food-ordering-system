package com.food.ordering.system.order.service.application.exception.handler;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorResponse {

  String code;
  String message;

}
