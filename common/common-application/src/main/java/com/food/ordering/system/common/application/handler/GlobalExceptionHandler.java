package com.food.ordering.system.common.application.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  @ResponseBody
  @ExceptionHandler({Exception.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse handleException(final Exception exception) {
    log.error(exception.getMessage(), exception);
    return ErrorResponse.builder()
      .code(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
      .message("An unexpected error occurred")
      .build();
  }


  @ResponseBody
  @ExceptionHandler({ValidationException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleException(final ValidationException exception) {
    log.error(exception.getMessage(), exception);

    final var errorBuilder = ErrorResponse.builder()
      .code(HttpStatus.BAD_REQUEST.getReasonPhrase());

    if (exception instanceof ConstraintViolationException) {
      final String violations = this.extractViolationsFrom((ConstraintViolationException) exception);
      return  errorBuilder
        .message(violations)
        .build();
    }

    return errorBuilder
      .message(exception.getMessage())
      .build();
  }

  private String extractViolationsFrom(final ConstraintViolationException exception) {
    return exception.getConstraintViolations().stream()
      .map(ConstraintViolation::getMessage)
      .collect(Collectors.joining(", ", "[", "]"));
  }

}
