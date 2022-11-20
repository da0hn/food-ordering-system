package com.food.ordering.system.kafka.producer.exception;


import java.text.MessageFormat;

public class KafkaProducerException extends RuntimeException {

  public KafkaProducerException(final String message) {
    super(message);
  }

  public KafkaProducerException(
    final String message,
    final Throwable cause
  ) {
    super(
      message,
      cause
    );
  }

  public KafkaProducerException(
    final Throwable cause,
    final String message,
    final Object... arguments
  ) {
    super(
      MessageFormat.format(message, arguments),
      cause
    );
  }

}
