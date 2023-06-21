package com.food.ordering.system.restaurant.service.domain;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.restaurant.service.domain.events.OrderApprovedEvent;
import com.food.ordering.system.restaurant.service.domain.events.OrderRejectedEvent;
import com.food.ordering.system.restaurant.service.domain.service.RestaurantDomainService;
import com.food.ordering.system.restaurant.service.domain.service.RestaurantDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {


  @Bean
  public RestaurantDomainService restaurantDomainService(
    final DomainEventPublisher<OrderRejectedEvent> orderRejectedEventDomainEventPublisher,
    final DomainEventPublisher<OrderApprovedEvent> orderApprovedEventDomainEventPublisher
  ) {
    return new RestaurantDomainServiceImpl(
      orderRejectedEventDomainEventPublisher,
      orderApprovedEventDomainEventPublisher
    );
  }


}
