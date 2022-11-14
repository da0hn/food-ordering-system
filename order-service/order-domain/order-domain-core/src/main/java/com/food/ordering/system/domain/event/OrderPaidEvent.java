package com.food.ordering.system.domain.event;

import com.food.ordering.system.domain.entity.Order;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class OrderPaidEvent extends OrderEvent {


  public OrderPaidEvent(final Order order) {
    super(order, ZonedDateTime.now(ZoneId.of("UTC")));
  }

}
