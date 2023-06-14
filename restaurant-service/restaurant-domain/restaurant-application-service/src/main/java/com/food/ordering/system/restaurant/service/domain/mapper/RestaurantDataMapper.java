package com.food.ordering.system.restaurant.service.domain.mapper;

import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.domain.valueobject.OrderStatus;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.restaurant.service.domain.dto.RestaurantApprovalRequest;
import com.food.ordering.system.restaurant.service.domain.entity.OrderDetail;
import com.food.ordering.system.restaurant.service.domain.entity.Product;
import com.food.ordering.system.restaurant.service.domain.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RestaurantDataMapper {


  public Restaurant restaurantApprovalRequestToRestaurant(final RestaurantApprovalRequest request) {
    return Restaurant.builder()
      .restaurantId(RestaurantId.of(UUID.fromString(request.getRestaurantId())))
      .orderDetail(
        OrderDetail.builder()
          .orderId(OrderId.of(UUID.fromString(request.getOrderId())))
          .products(
            request.getProducts().stream()
              .map(product ->
                     Product.builder()
                       .productId(product.getId())
                       .quantity(product.getQuantity())
                       .build())
              .collect(Collectors.toList())
          )
          .totalAmount(new Money(request.getPrice()))
          .orderStatus(OrderStatus.valueOf(request.getRestaurantOrderStatus().name()))
          .build()
      )
      .build();
  }
}
