package com.food.ordering.system.restaurant.service.domain.events;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.restaurant.service.domain.entity.OrderApproval;
import com.food.ordering.system.restaurant.service.domain.entity.Restaurant;

import java.time.ZonedDateTime;
import java.util.List;

public final class OrderApprovedEvent extends OrderApprovalEvent<DomainEventPublisher<OrderApprovedEvent>> {


  private OrderApprovedEvent(
    final DomainEventPublisher<OrderApprovedEvent> eventPublisher,
    final OrderApproval orderApproval,
    final RestaurantId restaurantId,
    final List<String> failureMessages,
    final ZonedDateTime createdAt
  ) {
    super(eventPublisher, orderApproval, restaurantId, failureMessages, createdAt);
  }

  public static OrderApprovalEvent<DomainEventPublisher<OrderApprovedEvent>> newInstance(
    final DomainEventPublisher<OrderApprovedEvent> eventPublisher,
    final Restaurant restaurant,
    final List<String> failureMessages
  ) {
    return new OrderApprovedEvent(
      eventPublisher,
      restaurant.getOrderApproval(),
      restaurant.getId(),
      failureMessages,
      ZonedDateTime.now()
    );
  }

  @Override
  public void fire() {
    this.eventPublisher.publish(this);
  }
}
