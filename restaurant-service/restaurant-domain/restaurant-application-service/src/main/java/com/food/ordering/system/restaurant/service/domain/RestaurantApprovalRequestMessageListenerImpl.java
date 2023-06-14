package com.food.ordering.system.restaurant.service.domain;

import com.food.ordering.system.restaurant.service.domain.dto.RestaurantApprovalRequest;
import com.food.ordering.system.restaurant.service.domain.events.OrderApprovalEvent;
import com.food.ordering.system.restaurant.service.domain.events.OrderApprovedEvent;
import com.food.ordering.system.restaurant.service.domain.events.OrderRejectedEvent;
import com.food.ordering.system.restaurant.service.domain.ports.api.message.listener.RestaurantApprovalRequestMessageListener;
import com.food.ordering.system.restaurant.service.domain.ports.spi.message.publisher.OrderApprovedMessagePublisher;
import com.food.ordering.system.restaurant.service.domain.ports.spi.message.publisher.OrderRejectedMessagePublisher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class RestaurantApprovalRequestMessageListenerImpl implements RestaurantApprovalRequestMessageListener {

  private final RestaurantApprovalRequestHelper restaurantApprovalRequestHelper;
  private final OrderApprovedMessagePublisher orderApprovedMessagePublisher;
  private final OrderRejectedMessagePublisher orderRejectedMessagePublisher;

  @Override
  public void approveOrder(final RestaurantApprovalRequest restaurantApprovalRequest) {
    final var orderApprovalEvent = this.restaurantApprovalRequestHelper.persistOrderApproval(restaurantApprovalRequest);
    this.fire(orderApprovalEvent);
  }

  private void fire(final OrderApprovalEvent orderApprovalEvent) {
    switch (orderApprovalEvent) {
      case final OrderApprovedEvent event -> this.orderApprovedMessagePublisher.publish(event);
      case final OrderRejectedEvent event -> this.orderRejectedMessagePublisher.publish(event);
    }
  }
}
