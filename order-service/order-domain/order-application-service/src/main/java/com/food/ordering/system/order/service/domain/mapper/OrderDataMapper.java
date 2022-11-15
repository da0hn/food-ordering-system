package com.food.ordering.system.order.service.domain.mapper;

import com.food.ordering.system.domain.entity.Order;
import com.food.ordering.system.domain.entity.OrderItem;
import com.food.ordering.system.domain.entity.Product;
import com.food.ordering.system.domain.entity.Restaurant;
import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.domain.valueobject.StreetAddress;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.create.OrderAddress;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class OrderDataMapper {


  public Restaurant createOrderCommandToRestaurant(final CreateOrderCommand command) {
    return Restaurant.builder()
      .id(RestaurantId.of(command.getRestaurantId()))
      .products(
        command.getItems()
          .stream()
          .map(item -> Product.of(ProductId.of(item.getProductId())))
          .toList()
      )
      .build();
  }

  public Order createOrderCommandToOrder(final CreateOrderCommand command) {
    return Order.builder()
      .customerId(CustomerId.of(command.getCustomerId()))
      .restaurantId(RestaurantId.of(command.getRestaurantId()))
      .deliveryAddress(this.orderAddressToStreetAddress(command.getAddress()))
      .price(new Money(command.getPrice()))
      .items(this.orderItemsToOrderItemEntities(command.getItems()))
      .build();
  }

  private StreetAddress orderAddressToStreetAddress(final OrderAddress address) {
    return new StreetAddress(
      UUID.randomUUID(),
      address.getStreet(),
      address.getPostalCode(),
      address.getCity()
    );
  }

  private List<OrderItem> orderItemsToOrderItemEntities(
    final List<com.food.ordering.system.order.service.domain.dto.create.OrderItem> items
  ) {
    return items.stream()
      .map(item -> OrderItem.builder()
        .product(Product.of(ProductId.of(item.getProductId())))
        .price(new Money(item.getPrice()))
        .quantity(item.getQuantity())
        .subTotal(new Money(item.getSubTotal()))
        .build()
      )
      .toList();
  }

  public CreateOrderResponse orderToCreateOrderResponse(final Order order) {
    return CreateOrderResponse.builder()
      .orderTrackingId(order.getTrackingId().getValue())
      .orderStatus(order.getOrderStatus())
      .build();
  }

}
