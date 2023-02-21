package com.food.ordering.system.order.service.messaging.mapper;

import com.food.ordering.system.domain.entity.Order;
import com.food.ordering.system.domain.event.OrderCancelledEvent;
import com.food.ordering.system.domain.event.OrderCreatedEvent;
import com.food.ordering.system.kafka.order.avro.model.PaymentOrderStatus;
import com.food.ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderMessagingDataMapper {

  public PaymentRequestAvroModel orderCreatedEventToPaymentRequestAvroModel(OrderCreatedEvent event) {
    final var order = event.getOrder();
    return PaymentRequestAvroModel.newBuilder()
      .setId(UUID.randomUUID().toString())
      .setSagaId("")
      .setOrderId(order.getId().getValue().toString())
      .setCustomerId(order.getCustomerId().getValue().toString())
      .setPrice(order.getPrice().getAmount())
      .setCreatedAt(event.getCreatedAt().toInstant())
      .setPaymentOrderStatus(PaymentOrderStatus.PENDING)
      .build();
  }


  public PaymentRequestAvroModel orderCancelledEventToPaymentRequestAvroModel(OrderCancelledEvent event) {
    final var order = event.getOrder();
    return PaymentRequestAvroModel.newBuilder()
      .setId(UUID.randomUUID().toString())
      .setSagaId("")
      .setOrderId(order.getId().getValue().toString())
      .setCustomerId(order.getCustomerId().getValue().toString())
      .setPrice(order.getPrice().getAmount())
      .setCreatedAt(event.getCreatedAt().toInstant())
      .setPaymentOrderStatus(PaymentOrderStatus.CANCELLED)
      .build();
  }

}
