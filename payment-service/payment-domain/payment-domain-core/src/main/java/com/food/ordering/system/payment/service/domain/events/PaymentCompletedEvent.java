package com.food.ordering.system.payment.service.domain.events;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.payment.service.domain.entity.Payment;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public final class PaymentCompletedEvent extends PaymentEvent<DomainEventPublisher<PaymentCompletedEvent>> {
  private PaymentCompletedEvent(
    final DomainEventPublisher<PaymentCompletedEvent> eventPublisher,
    final Payment payment,
    final ZonedDateTime createdAt
  ) {
    super(eventPublisher, payment, createdAt, new ArrayList<>());
  }

  public static PaymentEvent<DomainEventPublisher<PaymentCompletedEvent>> newInstance(
    final DomainEventPublisher<PaymentCompletedEvent> eventPublisher,
    final Payment payment
  ) {
    return new PaymentCompletedEvent(
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
