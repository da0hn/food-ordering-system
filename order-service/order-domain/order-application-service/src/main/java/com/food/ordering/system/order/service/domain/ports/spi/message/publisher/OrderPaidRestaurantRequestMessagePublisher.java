package com.food.ordering.system.order.service.domain.ports.spi.message.publisher;

import com.food.ordering.system.domain.event.OrderPaidEvent;
import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;

public interface OrderPaidRestaurantRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
