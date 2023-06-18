package com.food.ordering.system.domain.event;

import com.food.ordering.system.domain.entity.Order;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class OrderCreatedEvent extends OrderEvent {


  public OrderCreatedEvent(final Order order) {
    super(eventPublisher, order, ZonedDateTime.now(ZoneId.of("UTC")));
  }

}
