package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.domain.event.EmptyEvent;
import com.food.ordering.system.domain.event.OrderCancelledEvent;
import com.food.ordering.system.domain.service.OrderDomainService;
import com.food.ordering.system.order.service.domain.dto.message.RestaurantApprovalResponse;
import com.food.ordering.system.saga.SagaStep;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@AllArgsConstructor
public class OrderApprovalSaga implements SagaStep<RestaurantApprovalResponse, EmptyEvent, OrderCancelledEvent> {

  private final OrderDomainService orderDomainService;
  private final OrderSagaHelper orderSagaHelper;

  @Override
  @Transactional
  public EmptyEvent process(final RestaurantApprovalResponse data) {
    log.info("Approval order with id: {}", data.getOrderId());
    final var order = this.orderSagaHelper.findOrder(data.getOrderId());
    this.orderDomainService.approveOrder(order);
    this.orderSagaHelper.saveOrder(order);
    log.info("Order with id: {} was approved", order.getId().getValue());
    return EmptyEvent.empty();
  }

  @Override
  @Transactional
  public OrderCancelledEvent rollback(final RestaurantApprovalResponse data) {
    log.info("Cancelling order with id: {}", data.getOrderId());
    final var order = this.orderSagaHelper.findOrder(data.getOrderId());
    final var event = this.orderDomainService.cancelOrderPayment(order, data.getFailureMessages());
    this.orderSagaHelper.saveOrder(order);
    log.info("Order with id: {} is cancelling", order.getId().getValue());
    return event;
  }


}
