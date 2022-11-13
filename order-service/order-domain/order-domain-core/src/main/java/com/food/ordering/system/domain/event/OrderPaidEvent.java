package com.food.ordering.system.domain.event;

import com.food.ordering.system.domain.entity.Order;

import java.time.ZonedDateTime;

public class OrderPaidEvent extends OrderEvent {


  public OrderPaidEvent(
    final Order order,
    final ZonedDateTime createdAt
  ) {
    super(order, createdAt);
  }

}
