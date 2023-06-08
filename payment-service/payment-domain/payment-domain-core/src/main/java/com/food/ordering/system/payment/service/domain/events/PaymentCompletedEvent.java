package com.food.ordering.system.payment.service.domain.events;

import com.food.ordering.system.payment.service.domain.entity.Payment;

import java.time.ZonedDateTime;
import java.util.List;

public class PaymentCompletedEvent extends PaymentEvent {
  protected PaymentCompletedEvent(final Payment payment, final ZonedDateTime createdAt, final List<String> failureMessages) {
    super(payment, createdAt, failureMessages);
  }
}
