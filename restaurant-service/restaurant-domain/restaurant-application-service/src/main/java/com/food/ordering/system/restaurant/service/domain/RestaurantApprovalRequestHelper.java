package com.food.ordering.system.restaurant.service.domain;

import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.restaurant.service.domain.dto.RestaurantApprovalRequest;
import com.food.ordering.system.restaurant.service.domain.entity.Restaurant;
import com.food.ordering.system.restaurant.service.domain.events.OrderApprovalEvent;
import com.food.ordering.system.restaurant.service.domain.exception.RestaurantNotFoundException;
import com.food.ordering.system.restaurant.service.domain.mapper.RestaurantDataMapper;
import com.food.ordering.system.restaurant.service.domain.ports.spi.message.repository.OrderApprovalRepository;
import com.food.ordering.system.restaurant.service.domain.ports.spi.message.repository.RestaurantRepository;
import com.food.ordering.system.restaurant.service.domain.service.RestaurantDomainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class RestaurantApprovalRequestHelper {

  private final RestaurantDomainService restaurantDomainService;
  private final RestaurantDataMapper restaurantDataMapper;
  private final RestaurantRepository restaurantRepository;
  private final OrderApprovalRepository orderApprovalRepository;


  public OrderApprovalEvent<?> persistOrderApproval(final RestaurantApprovalRequest request) {
    log.info("Processing restaurant approval for order id: {}", request.getOrderId());
    final var failureMessages = new ArrayList<String>();
    final var restaurant = this.findRestaurant(request);
    final var orderApprovalEvent = this.restaurantDomainService.validateOrder(
      restaurant,
      failureMessages
    );
    this.orderApprovalRepository.save(restaurant.getOrderApproval());
    if (!failureMessages.isEmpty()) {
      log.error("Order validation failed for order id: {}", request.getOrderId());
      throw new IllegalStateException("Order validation failed for order id: " + request.getOrderId());
    }
    return orderApprovalEvent;
  }

  private Restaurant findRestaurant(final RestaurantApprovalRequest request) {
    final Restaurant restaurant = this.restaurantDataMapper.restaurantApprovalRequestToRestaurant(request);
    final var maybeRestaurantInformation = this.restaurantRepository.findRestaurantInformation(restaurant);
    if (maybeRestaurantInformation.isEmpty()) {
      log.error("Restaurant with id {} not found!", request.getOrderId());
      throw new RestaurantNotFoundException("Restaurant with id " + request.getOrderId() + " not found!");
    }
    final var restaurantInfo = maybeRestaurantInformation.get();
    restaurant.setActive(restaurantInfo.isActive());
    restaurant.getOrderDetail()
      .getProducts()
      .forEach(product -> {
        restaurantInfo.getOrderDetail()
          .getProducts().stream()
          .filter(p -> p.getId().equals(product.getId()))
          .findFirst()
          .ifPresent(info -> product.updateWithConfirmedNamePriceAndAvailability(
            info.getName(),
            info.getPrice(),
            info.isAvailable()
          ));
      });
    restaurant.getOrderDetail().setId(OrderId.of(UUID.fromString(request.getOrderId())));
    return restaurant;
  }

}
