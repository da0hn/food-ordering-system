package com.food.ordering.system.order.service.domain.ports.spi.repository;

import com.food.ordering.system.domain.entity.Restaurant;

import java.util.Optional;

public interface RestaurantRepository {

  Optional<Restaurant> findRestaurant(Restaurant restaurant);

}
