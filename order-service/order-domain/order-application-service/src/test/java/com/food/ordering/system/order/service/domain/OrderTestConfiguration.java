package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.domain.event.OrderCancelledEvent;
import com.food.ordering.system.domain.event.OrderCreatedEvent;
import com.food.ordering.system.domain.event.OrderPaidEvent;
import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.domain.service.OrderDomainService;
import com.food.ordering.system.domain.service.OrderDomainServiceImpl;
import com.food.ordering.system.order.service.domain.ports.spi.message.publisher.OrderCancelledPaymentRequestMessagePublisher;
import com.food.ordering.system.order.service.domain.ports.spi.message.publisher.OrderCreatedPaymentRequestMessagePublisher;
import com.food.ordering.system.order.service.domain.ports.spi.message.publisher.OrderPaidRestaurantRequestMessagePublisher;
import com.food.ordering.system.order.service.domain.ports.spi.repository.CustomerRepository;
import com.food.ordering.system.order.service.domain.ports.spi.repository.OrderRepository;
import com.food.ordering.system.order.service.domain.ports.spi.repository.RestaurantRepository;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.food.ordering.system")
public class OrderTestConfiguration {

  @Bean
  public OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher() {
    return Mockito.mock(OrderCreatedPaymentRequestMessagePublisher.class);
  }

  @Bean
  public OrderCancelledPaymentRequestMessagePublisher orderCancelledPaymentRequestMessagePublisher() {
    return Mockito.mock(OrderCancelledPaymentRequestMessagePublisher.class);
  }

  @Bean
  public OrderPaidRestaurantRequestMessagePublisher orderPaidRestaurantRequestMessagePublisher() {
    return Mockito.mock(OrderPaidRestaurantRequestMessagePublisher.class);
  }

  @Bean
  public OrderRepository orderRepository() {
    return Mockito.mock(OrderRepository.class);
  }

  @Bean
  public RestaurantRepository restaurantRepository() {
    return Mockito.mock(RestaurantRepository.class);
  }

  @Bean
  public CustomerRepository customerRepository() {
    return Mockito.mock(CustomerRepository.class);
  }

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
