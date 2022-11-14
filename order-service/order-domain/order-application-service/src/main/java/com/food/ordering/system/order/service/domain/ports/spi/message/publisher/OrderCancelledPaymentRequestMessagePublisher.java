package com.food.ordering.system.order.service.domain.ports.spi.message.publisher;

import com.food.ordering.system.domain.event.OrderCancelledEvent;
import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;

public interface OrderCancelledPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCancelledEvent> {
}
