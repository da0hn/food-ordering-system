package com.food.ordering.system.restaurant.service.domain.events;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.restaurant.service.domain.entity.OrderApproval;
import com.food.ordering.system.restaurant.service.domain.entity.Restaurant;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public final class OrderRejectedEvent extends OrderApprovalEvent<DomainEventPublisher<OrderRejectedEvent>> {

  private OrderRejectedEvent(
    final DomainEventPublisher<OrderRejectedEvent> eventPublisher,
    final OrderApproval orderApproval,
    final RestaurantId restaurantId,
    final List<String> failureMessages,
    final ZonedDateTime createdAt
  ) {
    super(eventPublisher, orderApproval, restaurantId, failureMessages, createdAt);
  }

  public static OrderApprovalEvent<DomainEventPublisher<OrderRejectedEvent>> newInstance(
    final DomainEventPublisher<OrderRejectedEvent> eventPublisher,
    final Restaurant restaurant, final List<String> failureMessages
  ) {
    return new OrderRejectedEvent(
      eventPublisher,
      restaurant.getOrderApproval(),
      restaurant.getId(),
      failureMessages,
      ZonedDateTime.now(ZoneId.of("UTC"))
    );
  }

  @Override
  public void fire() {
    this.eventPublisher.publish(this);
  }
}
