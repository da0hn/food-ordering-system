package com.food.ordering.system.payment.service.domain.events;

import com.food.ordering.system.domain.event.DomainEvent;
import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.payment.service.domain.entity.Payment;

import java.time.ZonedDateTime;
import java.util.List;

public abstract sealed class PaymentEvent<P extends DomainEventPublisher<?>> implements DomainEvent<Payment> permits PaymentCompletedEvent, PaymentCancelledEvent, PaymentFailedEvent {

  protected final P eventPublisher;
  private final Payment payment;
  private final ZonedDateTime createdAt;
  private final List<String> failureMessages;

  protected PaymentEvent(final P eventPublisher, final Payment payment, final ZonedDateTime createdAt, final List<String> failureMessages) {
    this.eventPublisher = eventPublisher;
    this.payment = payment;
    this.createdAt = createdAt;
    this.failureMessages = failureMessages;
  }

  public Payment getPayment() {
    return this.payment;
  }

  public ZonedDateTime getCreatedAt() {
    return this.createdAt;
  }

  public List<String> getFailureMessages() {
    return this.failureMessages;
  }
}
