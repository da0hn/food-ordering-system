package com.food.ordering.system.order.service.messaging.mapper;

import com.food.ordering.system.domain.event.OrderCancelledEvent;
import com.food.ordering.system.domain.event.OrderCreatedEvent;
import com.food.ordering.system.domain.event.OrderPaidEvent;
import com.food.ordering.system.kafka.order.avro.model.PaymentOrderStatus;
import com.food.ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import com.food.ordering.system.kafka.order.avro.model.Product;
import com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel;
import com.food.ordering.system.kafka.order.avro.model.RestaurantOrderStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderMessagingDataMapper {

  public PaymentRequestAvroModel orderCreatedEventToPaymentRequestAvroModel(final OrderCreatedEvent event) {
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


  public PaymentRequestAvroModel orderCancelledEventToPaymentRequestAvroModel(final OrderCancelledEvent event) {
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

  public RestaurantApprovalRequestAvroModel orderPaidEventToRestaurantApprovalRequestAvroModel(final OrderPaidEvent event) {
    final var order = event.getOrder();
    return RestaurantApprovalRequestAvroModel.newBuilder()
      .setId(UUID.randomUUID().toString())
      .setSagaId("")
      .setOrderId(order.getId().getValue().toString())
      .setRestaurantId(order.getRestaurantId().getValue().toString())
      .setRestaurantOrderStatus(RestaurantOrderStatus.valueOf(order.getOrderStatus().name()))
      .setPrice(order.getPrice().getAmount())
      .setCreatedAt(event.getCreatedAt().toInstant())
      .setRestaurantOrderStatus(RestaurantOrderStatus.PAID)
      .setProducts(
        order.getItems().stream()
          .map(item -> Product.newBuilder()
            .setId(item.getId().getValue().toString())
            .setQuantity(item.getQuantity())
            .build()
          )
          .collect(Collectors.toList())
      )
      .build();
  }


}
