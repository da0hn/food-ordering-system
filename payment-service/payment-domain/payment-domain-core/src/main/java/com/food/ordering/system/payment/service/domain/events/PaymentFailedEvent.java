package com.food.ordering.system.payment.service.domain.events;

import com.food.ordering.system.payment.service.domain.entity.Payment;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public non-sealed class PaymentFailedEvent extends PaymentEvent {
  protected PaymentFailedEvent(
    final Payment payment,
    final ZonedDateTime createdAt,
    final List<String> failureMessages
  ) {
    super(payment, createdAt, failureMessages);
  }

  public static PaymentEvent newInstance(final Payment payment, final List<String> failureMessages) {
    return new PaymentFailedEvent(
      payment,
      ZonedDateTime.now(ZoneId.of("UTC")),
      failureMessages
    );
  }
}
