package com.food.ordering.system.restaurant.service.domain.ports.api.message.listener;

import com.food.ordering.system.restaurant.service.domain.dto.RestaurantApprovalRequest;

public interface RestaurantApprovalRequestMessageListener {

  void approveOrder(RestaurantApprovalRequest restaurantApprovalRequest);

}
