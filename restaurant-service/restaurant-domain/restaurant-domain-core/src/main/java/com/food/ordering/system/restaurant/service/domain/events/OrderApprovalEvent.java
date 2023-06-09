package com.food.ordering.system.restaurant.service.domain.events;

import com.food.ordering.system.domain.event.DomainEvent;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.restaurant.service.domain.entity.OrderApproval;

import java.time.ZonedDateTime;
import java.util.List;

public abstract class OrderApprovalEvent implements DomainEvent<OrderApproval> {

  private final OrderApproval orderApproval;
  private final RestaurantId restaurantId;
  private final List<String> failureMessages;
  private final ZonedDateTime createdAt;

  protected OrderApprovalEvent(
    final OrderApproval orderApproval,
    final RestaurantId restaurantId,
    final List<String> failureMessages,
    final ZonedDateTime createdAt
  ) {
    this.orderApproval = orderApproval;
    this.restaurantId = restaurantId;
    this.failureMessages = failureMessages;
    this.createdAt = createdAt;
  }

  public OrderApproval getOrderApproval() {
    return this.orderApproval;
  }

  public RestaurantId getRestaurantId() {
    return this.restaurantId;
  }

  public List<String> getFailureMessages() {
    return this.failureMessages;
  }

  public ZonedDateTime getCreatedAt() {
    return this.createdAt;
  }
}
