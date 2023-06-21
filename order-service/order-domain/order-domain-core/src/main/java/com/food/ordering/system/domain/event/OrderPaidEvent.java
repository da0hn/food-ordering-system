package com.food.ordering.system.domain.event;

import com.food.ordering.system.domain.entity.Order;
import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class OrderPaidEvent extends OrderEvent<DomainEventPublisher<OrderPaidEvent>> {


  public OrderPaidEvent(
    final DomainEventPublisher<OrderPaidEvent> eventPublisher,
    final Order order
  ) {
    super(eventPublisher, order, ZonedDateTime.now(ZoneId.of("UTC")));
  }

  @Override
  public void fire() {
    this.eventPublisher.publish(this);
  }
}
