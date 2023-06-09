package com.food.ordering.system.restaurant.service.domain.service;

import com.food.ordering.system.domain.valueobject.OrderApprovalStatus;
import com.food.ordering.system.restaurant.service.domain.entity.Restaurant;
import com.food.ordering.system.restaurant.service.domain.events.OrderApprovalEvent;
import com.food.ordering.system.restaurant.service.domain.events.OrderApprovedEvent;
import com.food.ordering.system.restaurant.service.domain.events.OrderRejectedEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class RestaurantDomainServiceImpl implements RestaurantDomainService {


  @Override
  public OrderApprovalEvent validateOrder(final Restaurant restaurant, final List<String> failureMessages) {
    restaurant.validateOrder(failureMessages);
    log.info("Validating order with id: {}", restaurant.getId());
    if (!failureMessages.isEmpty()) {
      log.info("Order is rejected for order id: {}", restaurant.getOrderDetail().getId().getValue());
      restaurant.constructOrderApproval(OrderApprovalStatus.REJECTED);
      return OrderRejectedEvent.newInstance(restaurant, failureMessages);
    }
    log.info("Order is approved for order id: {}", restaurant.getOrderDetail().getId().getValue());
    restaurant.constructOrderApproval(OrderApprovalStatus.APPROVED);
    return OrderApprovedEvent.newInstance(restaurant, failureMessages);
  }
}
