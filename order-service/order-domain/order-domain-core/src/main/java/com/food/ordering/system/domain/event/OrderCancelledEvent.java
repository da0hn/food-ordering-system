package com.food.ordering.system.domain.event;

import com.food.ordering.system.domain.entity.Order;
import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class OrderCancelledEvent extends OrderEvent {


  public OrderCancelledEvent(final DomainEventPublisher<OrderCancelledEvent> eventPublisher, final Order order) {
    super(eventPublisher, order, ZonedDateTime.now(ZoneId.of("UTC"))
    );
  }

  @Override
  public void fire() {

  }
}
