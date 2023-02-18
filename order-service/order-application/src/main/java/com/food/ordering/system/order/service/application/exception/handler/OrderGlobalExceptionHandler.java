package com.food.ordering.system.order.service.application.exception.handler;

import com.food.ordering.system.common.application.handler.ErrorResponse;
import com.food.ordering.system.common.application.handler.GlobalExceptionHandler;
import com.food.ordering.system.domain.exception.OrderDomainException;
import com.food.ordering.system.domain.exception.OrderNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class OrderGlobalExceptionHandler extends GlobalExceptionHandler {

  @ResponseBody
  @ExceptionHandler({OrderDomainException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleException(final OrderDomainException exception) {
    log.error(exception.getMessage(), exception);
    return ErrorResponse.builder()
      .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
      .message(exception.getMessage())
      .build();
  }

  @ResponseBody
  @ExceptionHandler({OrderNotFoundException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse handleException(final OrderNotFoundException exception) {
    log.error(exception.getMessage(), exception);
    return ErrorResponse.builder()
      .code(HttpStatus.NOT_FOUND.getReasonPhrase())
      .message(exception.getMessage())
      .build();
  }

}
