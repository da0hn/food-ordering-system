package com.food.ordering.system.payment.service.dataaccess.payment.mapper;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.payment.service.domain.entity.Payment;
import com.food.ordering.system.payment.service.domain.valueobject.PaymentId;
import com.food.ordering.system.payment.service.dataaccess.payment.entity.PaymentEntity;
import org.springframework.stereotype.Component;

@Component
public class PaymentDataAccessMapper {

  public PaymentEntity paymentToPaymentEntity(final Payment payment) {
    return PaymentEntity.builder()
      .id(payment.getId().getValue())
      .customerId(payment.getCustomerId().getValue())
      .orderId(payment.getOrderId().getValue())
      .price(payment.getPrice().getAmount())
      .status(payment.getPaymentStatus())
      .createdAt(payment.getCreatedAt())
      .build();
  }

  public Payment paymentEntityToPayment(final PaymentEntity paymentEntity) {
    return Payment.builder()
      .paymentId(PaymentId.of(paymentEntity.getId()))
      .customerId(CustomerId.of(paymentEntity.getCustomerId()))
      .orderId(OrderId.of(paymentEntity.getOrderId()))
      .price(new Money(paymentEntity.getPrice()))
      .paymentStatus(paymentEntity.getStatus())
      .createdAt(paymentEntity.getCreatedAt())
      .build();
  }

}
