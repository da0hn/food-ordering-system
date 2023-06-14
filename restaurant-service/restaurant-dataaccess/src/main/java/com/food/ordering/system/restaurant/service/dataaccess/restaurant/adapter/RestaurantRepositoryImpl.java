package com.food.ordering.system.restaurant.service.dataaccess.restaurant.adapter;

import com.food.ordering.system.dataaccess.restaurant.repository.RestaurantJpaRepository;
import com.food.ordering.system.restaurant.service.dataaccess.restaurant.mapper.RestaurantDataAccessMapper;
import com.food.ordering.system.restaurant.service.domain.entity.Restaurant;
import com.food.ordering.system.restaurant.service.domain.ports.spi.message.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class RestaurantRepositoryImpl implements RestaurantRepository {

  private final RestaurantJpaRepository restaurantJpaRepository;
  private final RestaurantDataAccessMapper restaurantDataAccessMapper;

  @Override
  public Optional<Restaurant> findRestaurantInformation(final Restaurant restaurant) {
    final List<UUID> restaurantProducts = this.restaurantDataAccessMapper.restaurantToRestaurantProducts(restaurant);

    final var maybeRestaurantEntities = this.restaurantJpaRepository.findByRestaurantIdAndProductIdIn(
      restaurant.getId().getValue(),
      restaurantProducts
    );

    return maybeRestaurantEntities.map(this.restaurantDataAccessMapper::restaurantEntityToRestaurant);
  }
}
