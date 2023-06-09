package com.food.ordering.system.restaurant.service.domain.events;

import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.restaurant.service.domain.entity.OrderApproval;
import com.food.ordering.system.restaurant.service.domain.entity.Restaurant;

import java.time.ZonedDateTime;
import java.util.List;

public class OrderApprovedEvent extends OrderApprovalEvent {


  protected OrderApprovedEvent(
    final OrderApproval orderApproval,
    final RestaurantId restaurantId,
    final List<String> failureMessages,
    final ZonedDateTime createdAt
  ) {
    super(orderApproval, restaurantId, failureMessages, createdAt);
  }

  public static OrderApprovalEvent newInstance(final Restaurant restaurant, final List<String> failureMessages) {
    return new OrderApprovedEvent(
      restaurant.getOrderApproval(),
      restaurant.getId(),
      failureMessages,
      ZonedDateTime.now()
    );
  }
}
