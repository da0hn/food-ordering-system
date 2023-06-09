package com.food.ordering.system.restaurant.service.domain.service;

import com.food.ordering.system.restaurant.service.domain.entity.Restaurant;
import com.food.ordering.system.restaurant.service.domain.events.OrderApprovalEvent;

import java.util.List;

public interface RestaurantDomainService {

  OrderApprovalEvent validateOrder(Restaurant restaurant, List<String> failureMessages);

}
