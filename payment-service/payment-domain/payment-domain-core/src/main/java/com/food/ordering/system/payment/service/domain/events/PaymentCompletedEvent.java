package com.food.ordering.system.payment.service.domain.events;

import com.food.ordering.system.payment.service.domain.entity.Payment;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class PaymentCompletedEvent extends PaymentEvent {
  private PaymentCompletedEvent(
    final Payment payment,
    final ZonedDateTime createdAt
  ) {
    super(payment, createdAt, new ArrayList<>());
  }

  public static PaymentEvent newInstance(final Payment payment) {
    return new PaymentCompletedEvent(
      payment,
      ZonedDateTime.now(ZoneId.of("UTC"))
    );
  }
}
