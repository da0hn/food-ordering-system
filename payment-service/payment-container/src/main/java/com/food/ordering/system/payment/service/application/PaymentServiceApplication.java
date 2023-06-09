package com.food.ordering.system.payment.service.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.food.ordering.system.payment.service.dataaccess")
@EnableJpaRepositories(basePackages = "com.food.ordering.system.payment.service.dataaccess")
@SpringBootApplication(scanBasePackages = "com.food.ordering.system")
public class PaymentServiceApplication {

  public static void main(final String[] args) {
    SpringApplication.run(PaymentServiceApplication.class, args);
  }

}
