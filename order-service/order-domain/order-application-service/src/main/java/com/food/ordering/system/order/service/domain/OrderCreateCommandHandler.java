package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.domain.entity.Order;
import com.food.ordering.system.domain.entity.Restaurant;
import com.food.ordering.system.domain.exception.OrderDomainException;
import com.food.ordering.system.domain.service.OrderDomainService;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.system.order.service.domain.ports.spi.repository.CustomerRepository;
import com.food.ordering.system.order.service.domain.ports.spi.repository.OrderRepository;
import com.food.ordering.system.order.service.domain.ports.spi.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class OrderCreateCommandHandler {

  private final OrderDomainService orderDomainService;
  private final OrderRepository orderRepository;
  private final CustomerRepository customerRepository;
  private final RestaurantRepository restaurantRepository;
  private final ApplicationDomainEventPublisher applicationDomainEventPublisher;
  private final OrderDataMapper orderDataMapper;

  @Transactional
  public CreateOrderResponse handle(final CreateOrderCommand command) {
    this.checkCustomer(command.getCustomerId());
    final var restaurant = this.checkRestaurant(command);
    final var order = this.orderDataMapper.createOrderCommandToOrder(command);
    final var orderCreatedEvent = this.orderDomainService.validateAndInitiateOrder(order, restaurant);
    final var savedOrder = this.saveOrder(order);
    this.applicationDomainEventPublisher.publish(orderCreatedEvent);
    return this.orderDataMapper.orderToCreateOrderResponse(savedOrder);
  }

  private void checkCustomer(final UUID customerId) {
    final var maybeCustomer = this.customerRepository.findCustomer(customerId);
    if (maybeCustomer.isEmpty()) {
      log.warn("Could not find customer with customer id {}", customerId);
      throw new OrderDomainException("Could not find customer with id: {0}", customerId);
    }
  }

  private Restaurant checkRestaurant(final CreateOrderCommand command) {
    final var restaurant = this.orderDataMapper.createOrderCommandToRestaurant(command);
    final var maybeRestaurantInformation = this.restaurantRepository.findRestaurantInformation(restaurant);
    return maybeRestaurantInformation.orElseThrow(() -> {
      log.warn("Could not find restaurant with restaurant id: {}", command.getRestaurantId());
      return new OrderDomainException("Could not find restaurant with restaurant id: {0}", command.getRestaurantId());
    });
  }

  private Order saveOrder(final Order order) {
    final var maybeSavedOrder = this.orderRepository.save(order);
    final var savedOrder = maybeSavedOrder.orElseThrow(() -> {
      log.warn("Could not save order {}", order);
      return new OrderDomainException("Could not save order");
    });
    log.info("Order is saved with id: {}", savedOrder.getId().getValue());
    return savedOrder;
  }

}
