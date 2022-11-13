package com.food.ordering.system.domain.event;

import com.food.ordering.system.domain.entity.Order;

import java.time.ZonedDateTime;

public abstract class OrderEvent implements DomainEvent<Order> {

  private final Order order;
  private final ZonedDateTime createdAt;

  protected OrderEvent(
    final Order order,
    final ZonedDateTime createdAt
  ) {
    this.order = order;
    this.createdAt = createdAt;
  }

  public Order getOrder() {
    return this.order;
  }

  public ZonedDateTime getCreatedAt() {
    return this.createdAt;
  }

}
