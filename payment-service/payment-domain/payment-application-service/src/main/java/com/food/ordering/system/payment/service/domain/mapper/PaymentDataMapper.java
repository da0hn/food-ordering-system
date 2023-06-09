package com.food.ordering.system.payment.service.domain.mapper;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.payment.service.domain.entity.Payment;
import com.food.ordering.system.payment.service.domain.dto.PaymentRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentDataMapper {


  public Payment paymentRequestModelToPayment(final PaymentRequest paymentRequest) {
    return Payment.builder()
      .customerId(CustomerId.of(UUID.fromString(paymentRequest.getCustomerId())))
      .orderId(OrderId.of(UUID.fromString(paymentRequest.getOrderId())))
      .price(new Money(paymentRequest.getPrice()))
      .build();
  }
}
