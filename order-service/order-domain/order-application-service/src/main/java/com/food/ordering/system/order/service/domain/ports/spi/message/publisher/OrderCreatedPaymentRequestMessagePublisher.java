package com.food.ordering.system.order.service.domain.ports.spi.message.publisher;

import com.food.ordering.system.domain.event.OrderCreatedEvent;
import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;

public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {
}
