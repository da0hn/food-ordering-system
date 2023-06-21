package com.food.ordering.system.payment.service.domain.events;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.payment.service.domain.entity.Payment;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public final class PaymentCancelledEvent extends PaymentEvent<DomainEventPublisher<PaymentCancelledEvent>> {

  private PaymentCancelledEvent(
    final DomainEventPublisher<PaymentCancelledEvent> eventPublisher,
    final Payment payment,
    final ZonedDateTime createdAt
  ) {
    super(eventPublisher, payment, createdAt, new ArrayList<>());
  }

  public static PaymentEvent<DomainEventPublisher<PaymentCancelledEvent>> newInstance(
    final DomainEventPublisher<PaymentCancelledEvent> eventPublisher,
    final Payment payment
  ) {
    return new PaymentCancelledEvent(
      eventPublisher,
      payment,
      ZonedDateTime.now(ZoneId.of("UTC"))
    );
  }

  @Override
  public void fire() {
    this.eventPublisher.publish(this);
  }
}
