package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.domain.event.OrderCancelledEvent;
import com.food.ordering.system.domain.event.OrderCreatedEvent;
import com.food.ordering.system.domain.event.OrderPaidEvent;
import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.domain.service.OrderDomainService;
import com.food.ordering.system.domain.service.OrderDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {


  @Bean
  public OrderDomainService orderDomainService(
    final DomainEventPublisher<OrderCreatedEvent> orderCreatedEventDomainEventPublisher,
    final DomainEventPublisher<OrderCancelledEvent> orderCancelledEventDomainEventPublisher,
    final DomainEventPublisher<OrderPaidEvent> orderPaidEventDomainEventPublisher
  ) {
    return new OrderDomainServiceImpl(
      orderCreatedEventDomainEventPublisher,
      orderCancelledEventDomainEventPublisher,
      orderPaidEventDomainEventPublisher
    );
  }


}
