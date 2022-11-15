package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.system.order.service.domain.ports.spi.message.publisher.OrderCreatedPaymentRequestMessagePublisher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class OrderCreateCommandHandler {

  private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher;
  private final OrderDataMapper orderDataMapper;
  private final OrderCreateHelper orderCreateHelper;

  public CreateOrderResponse handle(final CreateOrderCommand command) {
    final var orderCreatedEvent = this.orderCreateHelper.persistOrder(command);
    this.orderCreatedPaymentRequestMessagePublisher.publish(orderCreatedEvent);
    log.info("Order is created with id: {}", orderCreatedEvent.getOrder().getId().getValue());
    return this.orderDataMapper.orderToCreateOrderResponse(orderCreatedEvent.getOrder());
  }


}
