package com.food.ordering.system.order.service.dataaccess.order.mapper;

import com.food.ordering.system.domain.entity.Order;
import com.food.ordering.system.domain.entity.OrderItem;
import com.food.ordering.system.domain.entity.Product;
import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.domain.valueobject.OrderItemId;
import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.domain.valueobject.StreetAddress;
import com.food.ordering.system.domain.valueobject.TrackingId;
import com.food.ordering.system.order.service.dataaccess.order.entity.OrderAddressEntity;
import com.food.ordering.system.order.service.dataaccess.order.entity.OrderEntity;
import com.food.ordering.system.order.service.dataaccess.order.entity.OrderItemEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.food.ordering.system.domain.entity.Order.FAILURE_MESSAGE_DELIMITER;

@Component
public class OrderDataAccessMapper {

  public OrderEntity orderToOrderEntity(final Order order) {
    final OrderEntity orderEntity = OrderEntity.builder()
      .id(order.getId().getValue())
      .customerId(order.getCustomerId().getValue())
      .restaurantId(order.getRestaurantId().getValue())
      .trackingId(order.getTrackingId().getValue())
      .address(this.deliveryAddressToAddressEntity(order.getDeliveryAddress()))
      .orderStatus(order.getOrderStatus())
      .price(order.getPrice().getAmount())
      .items(this.orderItemsToOrderItemEntities(order.getItems()))
      .failureMessages(
        Optional.ofNullable(order.getFailureMessages())
          .map(messages -> String.join(FAILURE_MESSAGE_DELIMITER, messages))
          .orElse("")
      )
      .build();
    orderEntity.getAddress().setOrder(orderEntity);
    orderEntity.getItems().forEach(item -> item.setOrder(orderEntity));
    return orderEntity;
  }

  private OrderAddressEntity deliveryAddressToAddressEntity(final StreetAddress deliveryAddress) {
    return OrderAddressEntity.builder()
      .id(deliveryAddress.getId())
      .postalCode(deliveryAddress.getPostalCode())
      .street(deliveryAddress.getStreet())
      .city(deliveryAddress.getCity())
      .build();
  }

  private List<OrderItemEntity> orderItemsToOrderItemEntities(final Collection<? extends OrderItem> items) {
    return items.stream()
      .map(item -> OrderItemEntity.builder()
        .id(item.getId().getValue())
        .productId(item.getProduct().getId().getValue())
        .price(item.getPrice().getAmount())
        .quantity(item.getQuantity())
        .subTotal(item.getSubTotal().getAmount())
        .build()
      ).collect(Collectors.toList());
  }

  public Order orderEntityToOrder(final OrderEntity orderEntity) {
    return Order.builder()
      .orderId(OrderId.of(orderEntity.getId()))
      .customerId(CustomerId.of(orderEntity.getCustomerId()))
      .restaurantId(RestaurantId.of(orderEntity.getRestaurantId()))
      .deliveryAddress(this.addressEntityToDeliveryAddress(orderEntity.getAddress()))
      .price(new Money(orderEntity.getPrice()))
      .items(this.orderItemEntitiesToOrderItems(orderEntity.getItems()))
      .trackingId(TrackingId.of(orderEntity.getTrackingId()))
      .orderStatus(orderEntity.getOrderStatus())
      .failureMessages(
        StringUtils.hasLength(orderEntity.getFailureMessages()) ?
          new ArrayList<>(Arrays.asList(orderEntity.getFailureMessages().split(FAILURE_MESSAGE_DELIMITER)))
          : new ArrayList<>()
      )
      .build();
  }

  private StreetAddress addressEntityToDeliveryAddress(final OrderAddressEntity address) {
    return new StreetAddress(
      address.getId(),
      address.getStreet(),
      address.getPostalCode(),
      address.getCity()
    );
  }

  private List<OrderItem> orderItemEntitiesToOrderItems(final List<OrderItemEntity> items) {
    return items.stream()
      .map(item -> OrderItem.builder()
        .id(new OrderItemId(item.getId()))
        .orderId(OrderId.of(item.getOrder().getId()))
        .product(Product.of(ProductId.of(item.getProductId())))
        .price(new Money(item.getPrice()))
        .subTotal(new Money(item.getSubTotal()))
        .quantity(item.getQuantity())
        .build()
      ).collect(Collectors.toList());
  }

}
