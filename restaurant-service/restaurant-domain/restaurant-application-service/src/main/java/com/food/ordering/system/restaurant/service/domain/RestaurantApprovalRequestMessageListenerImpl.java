package com.food.ordering.system.restaurant.service.domain;

import com.food.ordering.system.restaurant.service.domain.dto.RestaurantApprovalRequest;
import com.food.ordering.system.restaurant.service.domain.ports.api.message.listener.RestaurantApprovalRequestMessageListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class RestaurantApprovalRequestMessageListenerImpl implements RestaurantApprovalRequestMessageListener {

  private final RestaurantApprovalRequestHelper restaurantApprovalRequestHelper;

  @Override
  public void approveOrder(final RestaurantApprovalRequest restaurantApprovalRequest) {
    final var event = this.restaurantApprovalRequestHelper.persistOrderApproval(restaurantApprovalRequest);
    event.fire();
  }

}
