package com.food.ordering.system.domain.event;

import com.food.ordering.system.domain.entity.Order;

import java.time.ZonedDateTime;

public class OrderCancelledEvent extends OrderEvent {


  public OrderCancelledEvent(
    final Order order,
    final ZonedDateTime createdAt
  ) {
    super(order, createdAt);
  }

}
