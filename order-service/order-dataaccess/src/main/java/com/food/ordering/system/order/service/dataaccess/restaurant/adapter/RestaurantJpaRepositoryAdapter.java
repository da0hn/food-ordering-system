package com.food.ordering.system.order.service.dataaccess.restaurant.adapter;

import com.food.ordering.system.domain.entity.Restaurant;
import com.food.ordering.system.order.service.dataaccess.restaurant.mapper.RestaurantDataAccessMapper;
import com.food.ordering.system.order.service.dataaccess.restaurant.repository.RestaurantJpaRepository;
import com.food.ordering.system.order.service.domain.ports.spi.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class RestaurantJpaRepositoryAdapter implements RestaurantRepository {

  private final RestaurantJpaRepository repository;
  private final RestaurantDataAccessMapper mapper;


  @Override
  public Optional<Restaurant> findRestaurantInformation(final Restaurant restaurant) {
    return this.repository.findByRestaurantIdAndProductIdIn(
      restaurant.getId().getValue(),
      this.mapper.restaurantToRestaurantProducts(restaurant)
    ).map(this.mapper::restaurantEntityToRestaurant);
  }

}
