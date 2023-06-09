package com.food.ordering.system.payment.service.application;

import com.food.ordering.system.payment.service.domain.service.PaymentDomainService;
import com.food.ordering.system.payment.service.domain.service.PaymentDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

  @Bean
  public PaymentDomainService paymentDomainService() {
    return new PaymentDomainServiceImpl();
  }

}
