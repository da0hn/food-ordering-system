package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.domain.event.EmptyEvent;
import com.food.ordering.system.domain.event.OrderPaidEvent;
import com.food.ordering.system.domain.service.OrderDomainService;
import com.food.ordering.system.order.service.domain.dto.message.PaymentResponse;
import com.food.ordering.system.saga.SagaStep;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@AllArgsConstructor
public class OrderPaymentSaga implements SagaStep<PaymentResponse, OrderPaidEvent, EmptyEvent> {

  private final OrderDomainService orderDomainService;
  private final OrderSagaHelper orderSagaHelper;

  @Override
  @Transactional
  public OrderPaidEvent process(final PaymentResponse data) {
    log.info("Completing payment for order with id: {}", data.getOrderId());
    final var order = this.orderSagaHelper.findOrder(data.getOrderId());
    final var event = this.orderDomainService.payOrder(order);
    this.orderSagaHelper.saveOrder(order);
    log.info("Order with id: {} is paid", order.getId().getValue());
    return event;
  }

  @Override
  @Transactional
  public EmptyEvent rollback(final PaymentResponse data) {
    log.info("Cancelling order with id: {}", data.getOrderId());
    final var order = this.orderSagaHelper.findOrder(data.getOrderId());
    this.orderDomainService.cancelOrder(order, data.getFailureMessages());
    this.orderSagaHelper.saveOrder(order);
    log.info("Order with id: {} was cancelled", data.getOrderId());
    return EmptyEvent.empty();
  }
}
