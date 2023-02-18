package com.food.ordering.system.common.application.handler;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorResponse {

  String code;
  String message;

}
