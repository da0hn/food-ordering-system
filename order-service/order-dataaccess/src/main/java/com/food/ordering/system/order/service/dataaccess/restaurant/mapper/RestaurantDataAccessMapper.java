package com.food.ordering.system.order.service.dataaccess.restaurant.mapper;

import com.food.ordering.system.dataaccess.restaurant.entity.RestaurantEntity;
import com.food.ordering.system.dataaccess.restaurant.exception.RestaurantDataAccessException;
import com.food.ordering.system.domain.entity.Product;
import com.food.ordering.system.domain.entity.Restaurant;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RestaurantDataAccessMapper {

  public List<UUID> restaurantToRestaurantProducts(final Restaurant restaurant) {
    return restaurant.getProducts().stream()
      .map(product -> product.getId().getValue())
      .collect(Collectors.toList());
  }

  public Restaurant restaurantEntityToRestaurant(final List<RestaurantEntity> restaurantEntities) {
    final RestaurantEntity restaurantEntity = restaurantEntities.stream()
      .findFirst()
      .orElseThrow(() -> new RestaurantDataAccessException("Restaurant could not be found"));

    final List<Product> restaurantProducts = restaurantEntities.stream()
      .map(restaurant -> Product.builder()
        .id(ProductId.of(restaurant.getProductId()))
        .name(restaurant.getProductName())
        .price(new Money(restaurant.getProductPrice()))
        .build()
      )
      .toList();

    return Restaurant.builder()
      .id(RestaurantId.of(restaurantEntity.getRestaurantId()))
      .active(restaurantEntity.getRestaurantActive())
      .products(new ArrayList<>(restaurantProducts))
      .build();
  }

}
