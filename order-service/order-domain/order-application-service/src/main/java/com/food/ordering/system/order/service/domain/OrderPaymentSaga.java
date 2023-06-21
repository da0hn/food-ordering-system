package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.domain.entity.Order;
import com.food.ordering.system.domain.event.EmptyEvent;
import com.food.ordering.system.domain.event.OrderPaidEvent;
import com.food.ordering.system.domain.exception.OrderNotFoundException;
import com.food.ordering.system.domain.service.OrderDomainService;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.order.service.domain.dto.message.PaymentResponse;
import com.food.ordering.system.order.service.domain.ports.spi.message.publisher.OrderPaidRestaurantRequestMessagePublisher;
import com.food.ordering.system.order.service.domain.ports.spi.repository.OrderRepository;
import com.food.ordering.system.saga.SagaStep;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class OrderPaymentSaga implements SagaStep<PaymentResponse, OrderPaidEvent, EmptyEvent> {

  private final OrderDomainService orderDomainService;
  private final OrderRepository orderRepository;
  private final OrderPaidRestaurantRequestMessagePublisher orderPaidRestaurantRequestMessagePublisher;

  @Override
  @Transactional
  public OrderPaidEvent process(final PaymentResponse data) {
    log.info("Completing payment for order with id: {}", data.getOrderId());
    final var order = this.findOrder(data.getOrderId());
    final var event = this.orderDomainService.payOrder(order);
    this.orderRepository.save(order);
    log.info("Order with id: {} is paid", order.getId().getValue());
    return event;
  }

  private Order findOrder(final String orderId) {
    return this.orderRepository.findById(OrderId.of(UUID.fromString(orderId)))
      .orElseThrow(() -> {
        log.error("Order with id: {} not found", orderId);
        return new OrderNotFoundException("Order with id: {} could not be found", orderId);
      });
  }

  @Override
  @Transactional
  public EmptyEvent rollback(final PaymentResponse data) {
    log.info("Cancelling order with id: {}", data.getOrderId());
    final var order = this.findOrder(data.getOrderId());
    this.orderDomainService.cancelOrder(order, data.getFailureMessages());
    this.orderRepository.save(order);
    log.info("Order with id: {} was cancelled", data.getOrderId());
    return EmptyEvent.empty();
  }
}
