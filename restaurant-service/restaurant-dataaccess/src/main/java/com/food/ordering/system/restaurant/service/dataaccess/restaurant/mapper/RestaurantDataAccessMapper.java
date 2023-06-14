package com.food.ordering.system.restaurant.service.dataaccess.restaurant.mapper;

import com.food.ordering.system.dataaccess.restaurant.entity.RestaurantEntity;
import com.food.ordering.system.dataaccess.restaurant.exception.RestaurantDataAccessException;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.restaurant.service.dataaccess.restaurant.entity.OrderApprovalEntity;
import com.food.ordering.system.restaurant.service.domain.entity.OrderApproval;
import com.food.ordering.system.restaurant.service.domain.entity.OrderDetail;
import com.food.ordering.system.restaurant.service.domain.entity.Product;
import com.food.ordering.system.restaurant.service.domain.entity.Restaurant;
import com.food.ordering.system.restaurant.service.domain.valueobject.OrderApprovalId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RestaurantDataAccessMapper {

  public List<UUID> restaurantToRestaurantProducts(final Restaurant restaurant) {
    return restaurant.getOrderDetail().getProducts().stream()
      .map(product -> product.getId().getValue())
      .toList();
  }

  public Restaurant restaurantEntityToRestaurant(final List<RestaurantEntity> restaurantEntities) {
    final RestaurantEntity restaurantEntity = restaurantEntities.stream()
      .findFirst()
      .orElseThrow(() -> new RestaurantDataAccessException("No restaurants found"));

    final List<Product> restaurantProducts = restaurantEntities.stream()
      .map(entity ->
             Product.builder()
               .productId(ProductId.of(entity.getProductId()))
               .name(entity.getProductName())
               .price(new Money(entity.getProductPrice()))
               .available(entity.getProductAvailable())
               .build()
      ).collect(Collectors.toList());
    return Restaurant.builder()
      .restaurantId(RestaurantId.of(restaurantEntity.getRestaurantId()))
      .orderDetail(
        OrderDetail.builder()
          .products(restaurantProducts)
          .build()
      )
      .active(restaurantEntity.getRestaurantActive())
      .build();
  }

  public OrderApprovalEntity orderApprovalToOrderApprovalEntity(final OrderApproval orderApproval) {
    return OrderApprovalEntity.builder()
      .id(orderApproval.getId().getValue())
      .restaurantId(orderApproval.getRestaurantId().getValue())
      .orderId(orderApproval.getOrderId().getValue())
      .status(orderApproval.getApprovalStatus())
      .build();
  }

  public OrderApproval orderApprovalEntityToOrderApproval(final OrderApprovalEntity orderApprovalEntity) {
    return OrderApproval.builder()
      .orderApprovalId(OrderApprovalId.of(orderApprovalEntity.getId()))
      .restaurantId(RestaurantId.of(orderApprovalEntity.getRestaurantId()))
      .orderId(OrderId.of(orderApprovalEntity.getOrderId()))
      .approvalStatus(orderApprovalEntity.getStatus())
      .build();
  }

}
