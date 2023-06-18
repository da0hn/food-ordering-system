package com.food.ordering.system.restaurant.service.domain.service;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.restaurant.service.domain.entity.Restaurant;
import com.food.ordering.system.restaurant.service.domain.events.OrderApprovalEvent;
import com.food.ordering.system.restaurant.service.domain.events.OrderApprovedEvent;
import com.food.ordering.system.restaurant.service.domain.events.OrderRejectedEvent;

import java.util.List;

@FunctionalInterface
public interface RestaurantDomainService {

  OrderApprovalEvent<?> validateOrder(
    final DomainEventPublisher<OrderRejectedEvent> orderRejectedEventDomainEventPublisher,
    final DomainEventPublisher<OrderApprovedEvent> orderApprovedEventDomainEventPublisher,
    Restaurant restaurant,
    List<String> failureMessages
  );

}
