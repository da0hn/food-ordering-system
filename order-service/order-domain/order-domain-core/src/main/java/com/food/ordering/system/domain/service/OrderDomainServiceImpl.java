package com.food.ordering.system.domain.service;

import com.food.ordering.system.domain.entity.Order;
import com.food.ordering.system.domain.entity.Restaurant;
import com.food.ordering.system.domain.event.OrderCancelledEvent;
import com.food.ordering.system.domain.event.OrderCreatedEvent;
import com.food.ordering.system.domain.event.OrderPaidEvent;
import com.food.ordering.system.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.system.domain.exception.OrderDomainException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {

  private final DomainEventPublisher<OrderCreatedEvent> orderCreatedEventDomainEventPublisher;
  private final DomainEventPublisher<OrderCancelledEvent> orderCancelledEventDomainEventPublisher;
  private final DomainEventPublisher<OrderPaidEvent> orderPaidEventDomainEventPublisher;

  public OrderDomainServiceImpl(
    final DomainEventPublisher<OrderCreatedEvent> orderCreatedEventDomainEventPublisher,
    final DomainEventPublisher<OrderCancelledEvent> orderCancelledEventDomainEventPublisher,
    final DomainEventPublisher<OrderPaidEvent> orderPaidEventDomainEventPublisher
  ) {
    this.orderCreatedEventDomainEventPublisher = orderCreatedEventDomainEventPublisher;
    this.orderCancelledEventDomainEventPublisher = orderCancelledEventDomainEventPublisher;
    this.orderPaidEventDomainEventPublisher = orderPaidEventDomainEventPublisher;
  }

  private static void validateRestaurant(final Restaurant restaurant) {
    if (!restaurant.isActive()) {
      throw new OrderDomainException(
        "Restaurant with id {0} is currently not active!",
        restaurant.getId().getValue()
      );
    }
  }

  private static void setOrderProductInformation(
    final Order order,
    final Restaurant restaurant
  ) {
    order.getItems().forEach(item -> {
      final var product = item.getProduct();

      final var maybeEquivalentProductInRestaurant = restaurant.getProducts().stream()
        .filter(p -> p.equals(product))
        .findFirst();

      maybeEquivalentProductInRestaurant.ifPresent(equivalentProduct -> {
        product.updateWithConfirmedNameAndPrice(
          equivalentProduct.getName(),
          equivalentProduct.getPrice()
        );
      });

    });
  }

  @Override
  public OrderCreatedEvent validateAndInitiateOrder(
    final Order order,
    final Restaurant restaurant
  ) {
    validateRestaurant(restaurant);
    setOrderProductInformation(order, restaurant);
    order.validateOrder();
    order.initializeOrder();
    log.info("Order with id: {} is initialized", order.getId().getValue());
    return new OrderCreatedEvent(this.orderCreatedEventDomainEventPublisher, order);
  }

  @Override
  public OrderPaidEvent payOrder(final Order order) {
    order.pay();
    log.info("Order with id: {} is paid", order.getId().getValue());
    return new OrderPaidEvent(this.orderPaidEventDomainEventPublisher, order);
  }

  @Override
  public void approveOrder(final Order order) {
    order.approve();
    log.info("Order with id: {} is approved", order.getId().getValue());
  }

  @Override
  public OrderCancelledEvent cancelOrderPayment(
    final Order order,
    final List<String> failureMessages
  ) {
    order.initializeCancel(failureMessages);
    log.info("Order payment is cancelling for order id: {}", order.getId().getValue());
    return new OrderCancelledEvent(this.orderCancelledEventDomainEventPublisher, order);
  }

  @Override
  public void cancelOrder(
    final Order order,
    final List<String> failureMessages
  ) {
    order.cancel(failureMessages);
    log.info("Order with id: {} is cancelled", order.getId().getValue());
  }

}
