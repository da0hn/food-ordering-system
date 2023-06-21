package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.domain.entity.Order;
import com.food.ordering.system.order.service.domain.dto.message.RestaurantApprovalResponse;
import com.food.ordering.system.order.service.domain.ports.api.message.listener.RestaurantApprovalResponseMessageListener;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
@AllArgsConstructor
public class RestaurantApprovalResponseMessageListenerImpl implements RestaurantApprovalResponseMessageListener {

  private final OrderApprovalSaga orderApprovalSaga;

  @Override
  public void orderApproved(final RestaurantApprovalResponse response) {
    final var event = this.orderApprovalSaga.process(response);
    log.info("Order is approved for order id: {}", response.getOrderId());
  }

  @Override
  public void orderRejected(final RestaurantApprovalResponse response) {
    final var event = this.orderApprovalSaga.rollback(response);
    log.info(
      "Publishing order cancelled event for order id: {} with failure messages: {}",
      response.getOrderId(),
      String.join(Order.FAILURE_MESSAGE_DELIMITER, response.getFailureMessages())
    );
    event.fire();
  }

}
