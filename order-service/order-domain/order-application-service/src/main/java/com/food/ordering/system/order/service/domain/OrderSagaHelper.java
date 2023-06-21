package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.domain.entity.Order;
import com.food.ordering.system.domain.exception.OrderNotFoundException;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.order.service.domain.ports.spi.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class OrderSagaHelper {

  private final OrderRepository orderRepository;

  public Order findOrder(final String orderId) {
    return this.orderRepository.findById(OrderId.of(UUID.fromString(orderId)))
      .orElseThrow(() -> {
        log.error("Order with id: {} not found", orderId);
        return new OrderNotFoundException("Order with id: {} could not be found", orderId);
      });
  }

  public void saveOrder(final Order order) {
    this.orderRepository.save(order);
  }
}
