package com.food.ordering.system.domain.entity;

import com.food.ordering.system.domain.exception.OrderDomainException;
import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.domain.valueobject.OrderItemId;
import com.food.ordering.system.domain.valueobject.OrderStatus;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.domain.valueobject.StreetAddress;
import com.food.ordering.system.domain.valueobject.TrackingId;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Order extends AggregateRoot<OrderId> {

  public static final String FAILURE_MESSAGE_DELIMITER = ",";
  private final CustomerId customerId;
  private final RestaurantId restaurantId;
  private final StreetAddress deliveryAddress;
  private final Money price;
  private final List<OrderItem> items;
  private List<String> failureMessages;
  private TrackingId trackingId;
  private OrderStatus orderStatus;

  private Order(final Builder builder) {
    super.setId(builder.id);
    this.customerId = builder.customerId;
    this.restaurantId = builder.restaurantId;
    this.deliveryAddress = builder.deliveryAddress;
    this.price = builder.price;
    this.items = builder.items;
    this.trackingId = builder.trackingId;
    this.orderStatus = builder.orderStatus;
    this.failureMessages = builder.failureMessages;
  }

  public static Builder builder() {
    return new Builder();
  }

  public void initializeOrder() {
    this.setId(OrderId.newInstance());
    this.trackingId = TrackingId.newInstance();
    this.orderStatus = OrderStatus.PENDING;
    this.initializeOrderItems();
  }

  private void initializeOrderItems() {
    long itemId = 1;
    for (final var orderItem : this.items) {
      orderItem.initializeOrderItem(this.getId(), new OrderItemId(itemId++));
    }
  }

  public void validateOrder() {
    this.validateInitialOrder();
    this.validateTotalPrice();
    this.validateItemsPrice();
  }

  private void validateInitialOrder() {
    if (this.orderStatus != null || this.getId() != null) {
      throw new OrderDomainException("Order is not in correct state for initialization!");
    }
  }

  private void validateTotalPrice() {
    if (this.price == null || !this.price.isGreaterThanZero()) {
      throw new OrderDomainException("Total price must be greater than zero!");
    }
  }

  private void validateItemsPrice() {
    final var orderItemsTotal = this.items.stream().map(orderItem -> {
      this.validateItemPrice(orderItem);
      return orderItem.getSubTotal();
    }).reduce(Money.ZERO, Money::add);

    if (!this.price.equals(orderItemsTotal)) {
      throw new OrderDomainException(
        "Total price: {0} is not equal to order items total: {1}",
        this.price.getAmount(),
        orderItemsTotal.getAmount()
      );
    }

  }

  private void validateItemPrice(final OrderItem orderItem) {
    if (!orderItem.isPriceValid()) {
      throw new OrderDomainException(
        "Order item price: {0} is not valid for product {1}",
        orderItem.getPrice().getAmount(),
        orderItem.getProduct().getId().getValue()
      );
    }
  }

  public void pay() {
    if (this.orderStatus != OrderStatus.PENDING) {
      throw new OrderDomainException("Order is not in correct state for pay operation!");
    }
    this.orderStatus = OrderStatus.PAID;
  }

  public void approve() {
    if (this.orderStatus != OrderStatus.PAID) {
      throw new OrderDomainException("Order is not in correct state for approve operation!");
    }
    this.orderStatus = OrderStatus.APPROVED;
  }

  public void initializeCancel(final Collection<String> failureMessages) {
    if (this.orderStatus != OrderStatus.PAID) {
      throw new OrderDomainException("Order is not in correct state for initialize cancel operation!");
    }
    this.orderStatus = OrderStatus.CANCELLING;
    this.updateFailureMessages(failureMessages);
  }

  private void updateFailureMessages(final Collection<String> failureMessages) {
    if (this.failureMessages == null) {
      this.failureMessages = new ArrayList<>();
    }
    this.failureMessages.addAll(
      failureMessages.stream()
        .filter(message -> !message.isEmpty() && !message.isBlank())
        .collect(Collectors.toSet())
    );
  }

  public void cancel(final Collection<String> failureMessages) {
    if (this.orderStatus != OrderStatus.CANCELLING && this.orderStatus != OrderStatus.PENDING) {
      throw new OrderDomainException("Order is not in correct state for cancel operation!");
    }
    this.orderStatus = OrderStatus.CANCELLED;
    this.updateFailureMessages(failureMessages);
  }

  public CustomerId getCustomerId() {
    return this.customerId;
  }

  public RestaurantId getRestaurantId() {
    return this.restaurantId;
  }

  public StreetAddress getDeliveryAddress() {
    return this.deliveryAddress;
  }

  public Money getPrice() {
    return this.price;
  }

  public List<OrderItem> getItems() {
    return Optional.ofNullable(this.items)
      .map(Collections::unmodifiableList)
      .orElse(new ArrayList<>());
  }

  public TrackingId getTrackingId() {
    return this.trackingId;
  }

  public OrderStatus getOrderStatus() {
    return this.orderStatus;
  }

  public List<String> getFailureMessages() {
    return Optional.ofNullable(this.failureMessages)
      .map(Collections::unmodifiableList)
      .orElse(new ArrayList<>());
  }

  public static final class Builder {

    private OrderId id;
    private CustomerId customerId;
    private RestaurantId restaurantId;
    private StreetAddress deliveryAddress;
    private Money price;
    private List<OrderItem> items;
    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> failureMessages;

    private Builder() {}

    public Builder orderId(final OrderId val) {
      this.id = val;
      return this;
    }

    public Builder customerId(final CustomerId val) {
      this.customerId = val;
      return this;
    }

    public Builder restaurantId(final RestaurantId val) {
      this.restaurantId = val;
      return this;
    }

    public Builder deliveryAddress(final StreetAddress val) {
      this.deliveryAddress = val;
      return this;
    }

    public Builder price(final Money val) {
      this.price = val;
      return this;
    }

    public Builder items(final List<OrderItem> val) {
      this.items = val;
      return this;
    }

    public Builder trackingId(final TrackingId val) {
      this.trackingId = val;
      return this;
    }

    public Builder orderStatus(final OrderStatus val) {
      this.orderStatus = val;
      return this;
    }

    public Builder failureMessages(final List<String> val) {
      this.failureMessages = val;
      return this;
    }

    public Order build() {
      return new Order(this);
    }

  }

}
