package com.food.ordering.system.restaurant.service.messaging.mapper;

import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantOrderStatus;
import com.food.ordering.system.kafka.order.avro.model.OrderApprovalStatus;
import com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel;
import com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalResponseAvroModel;
import com.food.ordering.system.restaurant.service.domain.dto.RestaurantApprovalRequest;
import com.food.ordering.system.restaurant.service.domain.entity.Product;
import com.food.ordering.system.restaurant.service.domain.events.OrderApprovedEvent;
import com.food.ordering.system.restaurant.service.domain.events.OrderRejectedEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RestaurantMessagingDataMapper {

  public RestaurantApprovalResponseAvroModel orderApprovedEventToRestaurantApprovalResponseAvroModel(final OrderApprovedEvent orderApprovedEvent) {
    return RestaurantApprovalResponseAvroModel.newBuilder()
      .setId(UUID.randomUUID().toString())
      .setSagaId("")
      .setOrderId(orderApprovedEvent.getOrderApproval().getOrderId().getValue().toString())
      .setRestaurantId(orderApprovedEvent.getRestaurantId().getValue().toString())
      .setCreatedAt(orderApprovedEvent.getCreatedAt().toInstant())
      .setOrderApprovalStatus(OrderApprovalStatus.valueOf(orderApprovedEvent.getOrderApproval().getApprovalStatus().name()))
      .setFailureMessages(orderApprovedEvent.getFailureMessages())
      .build();
  }

  public RestaurantApprovalResponseAvroModel orderRejectedEventToRestaurantApprovalResponseAvroModel(final OrderRejectedEvent orderRejectedEvent) {
    return RestaurantApprovalResponseAvroModel.newBuilder()
      .setId(UUID.randomUUID().toString())
      .setSagaId("")
      .setOrderId(orderRejectedEvent.getOrderApproval().getOrderId().getValue().toString())
      .setRestaurantId(orderRejectedEvent.getRestaurantId().getValue().toString())
      .setCreatedAt(orderRejectedEvent.getCreatedAt().toInstant())
      .setOrderApprovalStatus(OrderApprovalStatus.valueOf(orderRejectedEvent.getOrderApproval().getApprovalStatus().name()))
      .setFailureMessages(orderRejectedEvent.getFailureMessages())
      .build();
  }

  public RestaurantApprovalRequest restaurantApprovalRequestAvroModelToRestaurantApproval(
    final RestaurantApprovalRequestAvroModel restaurantApprovalRequestAvroModel
  ) {
    return RestaurantApprovalRequest.builder()
      .id(restaurantApprovalRequestAvroModel.getId())
      .sagaId(restaurantApprovalRequestAvroModel.getSagaId())
      .restaurantId(restaurantApprovalRequestAvroModel.getRestaurantId())
      .orderId(restaurantApprovalRequestAvroModel.getOrderId())
      .restaurantOrderStatus(RestaurantOrderStatus.valueOf(restaurantApprovalRequestAvroModel.getRestaurantOrderStatus().name()))
      .products(restaurantApprovalRequestAvroModel.getProducts().stream()
                  .map(avroModel -> Product.builder()
                    .productId(ProductId.of(UUID.fromString(avroModel.getId())))
                    .quantity(avroModel.getQuantity())
                    .build()
                  ).collect(Collectors.toList())
      )
      .price(restaurantApprovalRequestAvroModel.getPrice())
      .createdAt(restaurantApprovalRequestAvroModel.getCreatedAt())
      .build();
  }

}
