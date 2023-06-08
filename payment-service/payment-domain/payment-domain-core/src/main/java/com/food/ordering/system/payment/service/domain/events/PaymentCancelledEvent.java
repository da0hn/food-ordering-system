package com.food.ordering.system.payment.service.domain.events;

import com.food.ordering.system.payment.service.domain.entity.Payment;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class PaymentCancelledEvent extends PaymentEvent {
  protected PaymentCancelledEvent(
    final Payment payment,
    final ZonedDateTime createdAt
  ) {
    super(payment, createdAt, new ArrayList<>());
  }

  public static PaymentEvent newInstance(final Payment payment) {
    return new PaymentCancelledEvent(payment, ZonedDateTime.now(ZoneId.of("UTC")));
  }
}
