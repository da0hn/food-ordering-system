package com.food.ordering.system.payment.service.domain.ports.spi.message.publisher;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.payment.service.domain.events.PaymentCancelledEvent;

@FunctionalInterface
public interface PaymentCancelledMessagePublisher extends DomainEventPublisher<PaymentCancelledEvent> {
}
