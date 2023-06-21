package com.food.ordering.system.payment.service.application;

import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.payment.service.domain.events.PaymentCancelledEvent;
import com.food.ordering.system.payment.service.domain.events.PaymentCompletedEvent;
import com.food.ordering.system.payment.service.domain.events.PaymentFailedEvent;
import com.food.ordering.system.payment.service.domain.service.PaymentDomainService;
import com.food.ordering.system.payment.service.domain.service.PaymentDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

  @Bean
  public PaymentDomainService paymentDomainService(
    final DomainEventPublisher<PaymentCompletedEvent> paymentCompletedEventDomainEventPublisher,
    final DomainEventPublisher<PaymentCancelledEvent> paymentCancelledEventDomainEventPublisher,
    final DomainEventPublisher<PaymentFailedEvent> paymentFailedEventDomainEventPublisher
  ) {
    return new PaymentDomainServiceImpl(
      paymentCompletedEventDomainEventPublisher,
      paymentCancelledEventDomainEventPublisher,
      paymentFailedEventDomainEventPublisher
    );
  }

}
