package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.domain.event.OrderCreatedEvent;
import com.food.ordering.system.order.service.domain.ports.spi.message.publisher.OrderCreatedPaymentRequestMessagePublisher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@AllArgsConstructor
public class OrderCreatedEventApplicationListener {

  private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher;

  @TransactionalEventListener
  public void process(final OrderCreatedEvent event) {
    this.orderCreatedPaymentRequestMessagePublisher.publish(event);
  }

}
