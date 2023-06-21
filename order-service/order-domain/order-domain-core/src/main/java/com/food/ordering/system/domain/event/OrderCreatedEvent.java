package com.food.ordering.system.domain.event;

import com.food.ordering.system.domain.entity.Order;
import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class OrderCreatedEvent extends OrderEvent<DomainEventPublisher<OrderCreatedEvent>> {


  public OrderCreatedEvent(final DomainEventPublisher<OrderCreatedEvent> eventPublisher, final Order order) {
    super(eventPublisher, order, ZonedDateTime.now(ZoneId.of("UTC")));
  }

  @Override
  public void fire() {
    this.eventPublisher.publish(this);
  }
}
