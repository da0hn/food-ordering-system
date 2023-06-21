package com.food.ordering.system.payment.service.domain.events;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.payment.service.domain.entity.Payment;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public final class PaymentFailedEvent extends PaymentEvent<DomainEventPublisher<PaymentFailedEvent>> {


  private PaymentFailedEvent(
    final DomainEventPublisher<PaymentFailedEvent> eventPublisher,
    final Payment payment,
    final ZonedDateTime createdAt,
    final List<String> failureMessages
  ) {
    super(eventPublisher, payment, createdAt, failureMessages);
  }

  public static PaymentEvent<DomainEventPublisher<PaymentFailedEvent>> newInstance(
    final DomainEventPublisher<PaymentFailedEvent> eventPublisher,
    final Payment payment,
    final List<String> failureMessages
  ) {
    return new PaymentFailedEvent(
      eventPublisher,
      payment,
      ZonedDateTime.now(ZoneId.of("UTC")),
      failureMessages
    );
  }

  @Override
  public void fire() {
    this.eventPublisher.publish(this);
  }
}
