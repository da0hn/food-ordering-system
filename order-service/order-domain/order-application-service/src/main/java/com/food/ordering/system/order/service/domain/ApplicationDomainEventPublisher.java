package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.domain.event.OrderCreatedEvent;
import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationDomainEventPublisher implements ApplicationEventPublisherAware, DomainEventPublisher<OrderCreatedEvent> {

  private ApplicationEventPublisher applicationEventPublisher;


  @Override
  public void setApplicationEventPublisher(final ApplicationEventPublisher applicationEventPublisher) {
    this.applicationEventPublisher = applicationEventPublisher;
  }

  @Override
  public void publish(final OrderCreatedEvent event) {
    this.applicationEventPublisher.publishEvent(event);
    log.info("OrderCreatedEvent was published for order id: {}", event.getOrder().getId().getValue());
  }


}
