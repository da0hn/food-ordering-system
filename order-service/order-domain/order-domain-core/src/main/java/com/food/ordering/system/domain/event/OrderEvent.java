package com.food.ordering.system.domain.event;

import com.food.ordering.system.domain.entity.Order;
import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;

import java.time.ZonedDateTime;

public abstract class OrderEvent<P extends DomainEventPublisher<?>> implements DomainEvent<Order> {

  protected final P eventPublisher;
  private final Order order;
  private final ZonedDateTime createdAt;

  protected OrderEvent(
    final P eventPublisher,
    final Order order,
    final ZonedDateTime createdAt
  ) {
    this.eventPublisher = eventPublisher;
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
