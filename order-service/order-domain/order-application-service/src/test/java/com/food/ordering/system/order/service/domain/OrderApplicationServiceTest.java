package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.domain.entity.Customer;
import com.food.ordering.system.domain.entity.Order;
import com.food.ordering.system.domain.entity.Product;
import com.food.ordering.system.domain.entity.Restaurant;
import com.food.ordering.system.domain.exception.OrderDomainException;
import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.domain.valueobject.Money;
import com.food.ordering.system.domain.valueobject.OrderId;
import com.food.ordering.system.domain.valueobject.OrderStatus;
import com.food.ordering.system.domain.valueobject.ProductId;
import com.food.ordering.system.domain.valueobject.RestaurantId;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.create.OrderAddress;
import com.food.ordering.system.order.service.domain.dto.create.OrderItem;
import com.food.ordering.system.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.system.order.service.domain.ports.api.service.OrderApplicationService;
import com.food.ordering.system.order.service.domain.ports.spi.repository.CustomerRepository;
import com.food.ordering.system.order.service.domain.ports.spi.repository.OrderRepository;
import com.food.ordering.system.order.service.domain.ports.spi.repository.RestaurantRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.from;
import static org.mockito.ArgumentMatchers.any;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = OrderTestConfiguration.class)
class OrderApplicationServiceTest {

  private static final UUID CUSTOMER_ID = UUID.fromString("0f2d9056-04d5-4c01-a183-baab1ba8eb02");
  private static final UUID RESTAURANT_ID = UUID.fromString("2f3b21f9-532f-4894-923e-3b7b6a68b68e");
  private static final UUID PRODUCT_ID = UUID.fromString("fe2f8236-1d70-4885-bc3f-54ee1f371cdc");
  private static final UUID ORDER_ID = UUID.fromString("f54f0a97-3631-47e7-bd91-1e9c3af65043");
  private static final BigDecimal PRICE = new BigDecimal("200.00");
  @Autowired
  private OrderApplicationService orderApplicationService;
  @Autowired
  private OrderDataMapper orderDataMapper;
  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private RestaurantRepository restaurantRepository;
  private CreateOrderCommand createOrderCommand;
  private CreateOrderCommand createOrderCommandWrongTotalPrice;
  private CreateOrderCommand createOrderCommandWrongProductPrice;

  @BeforeAll
  void init() {

    final var address = OrderAddress.builder()
      .street("street_1")
      .postalCode("1000AB")
      .city("Paris")
      .build();

    this.createOrderCommand = CreateOrderCommand.builder()
      .customerId(CUSTOMER_ID)
      .restaurantId(RESTAURANT_ID)
      .address(address)
      .price(PRICE)
      .items(List.of(
        OrderItem.builder()
          .productId(PRODUCT_ID)
          .quantity(1)
          .price(new BigDecimal("50.00"))
          .subTotal(new BigDecimal("50.00"))
          .build(),
        OrderItem.builder()
          .productId(PRODUCT_ID)
          .quantity(3)
          .price(new BigDecimal("50.00"))
          .subTotal(new BigDecimal("150.00"))
          .build()
      ))
      .build();

    this.createOrderCommandWrongTotalPrice = CreateOrderCommand.builder()
      .customerId(CUSTOMER_ID)
      .restaurantId(RESTAURANT_ID)
      .address(address)
      .price(new BigDecimal("250.00"))
      .items(List.of(
        OrderItem.builder()
          .productId(PRODUCT_ID)
          .quantity(1)
          .price(new BigDecimal("50.00"))
          .subTotal(new BigDecimal("50.00"))
          .build(),
        OrderItem.builder()
          .productId(PRODUCT_ID)
          .quantity(3)
          .price(new BigDecimal("50.00"))
          .subTotal(new BigDecimal("150.00"))
          .build()
      ))
      .build();

    this.createOrderCommandWrongProductPrice = CreateOrderCommand.builder()
      .customerId(CUSTOMER_ID)
      .restaurantId(RESTAURANT_ID)
      .address(address)
      .price(new BigDecimal("210.00"))
      .items(List.of(
        OrderItem.builder()
          .productId(PRODUCT_ID)
          .quantity(1)
          .price(new BigDecimal("60.00"))
          .subTotal(new BigDecimal("60.00"))
          .build(),
        OrderItem.builder()
          .productId(PRODUCT_ID)
          .quantity(3)
          .price(new BigDecimal("50.00"))
          .subTotal(new BigDecimal("150.00"))
          .build()
      ))
      .build();


    final var restaurant = Restaurant.builder()
      .id(RestaurantId.of(RESTAURANT_ID))
      .products(List.of(
        new Product(
          ProductId.of(PRODUCT_ID),
          "product-1",
          new Money(new BigDecimal("50.00"))
        ),
        new Product(
          ProductId.of(PRODUCT_ID),
          "product-2",
          new Money(new BigDecimal("50.00"))
        )
      ))
      .active(true)
      .build();

    Mockito.doReturn(Optional.of(restaurant))
      .when(this.restaurantRepository)
      .findRestaurantInformation(any(Restaurant.class));

    final var customer = new Customer();
    customer.setId(CustomerId.of(CUSTOMER_ID));
    Mockito.doReturn(Optional.of(customer))
      .when(this.customerRepository)
      .findCustomer(CUSTOMER_ID);

    final var order = this.orderDataMapper.createOrderCommandToOrder(this.createOrderCommand);
    order.setId(OrderId.of(ORDER_ID));
    Mockito.doReturn(Optional.of(order))
      .when(this.orderRepository)
      .save(any(Order.class));
  }

  @Test
  @DisplayName("Should create an Order correctly")
  void test1() {
    final var response = this.orderApplicationService.createOrder(this.createOrderCommand);
    Assertions.assertThat(response)
      .returns(OrderStatus.PENDING, from(CreateOrderResponse::getOrderStatus))
      .returns("Order created successfully", from(CreateOrderResponse::getMessage))
      .doesNotReturn(null, from(CreateOrderResponse::getOrderTrackingId));
  }

  @Test
  @DisplayName("Should not create an Order with wrong total price")
  void test2() {
    Assertions.assertThatExceptionOfType(OrderDomainException.class)
      .isThrownBy(() -> this.orderApplicationService.createOrder(this.createOrderCommandWrongTotalPrice))
      .withMessage("Total price: 250 is not equal to order items total: 200");
  }

  @Test
  @DisplayName("Should not create an Order with wrong product price")
  void test3() {
    Assertions.assertThatExceptionOfType(OrderDomainException.class)
      .isThrownBy(() -> this.orderApplicationService.createOrder(this.createOrderCommandWrongProductPrice))
      .withMessage("Order item price: 60 is not valid for product " + PRODUCT_ID);
  }

  @Test
  @DisplayName("Should not create an Order to inactive Restaurant")
  void test4() {
    final var restaurant = Restaurant.builder()
      .id(RestaurantId.of(RESTAURANT_ID))
      .products(List.of(
        new Product(
          ProductId.of(PRODUCT_ID),
          "product-1",
          new Money(new BigDecimal("50.00"))
        ),
        new Product(
          ProductId.of(PRODUCT_ID),
          "product-2",
          new Money(new BigDecimal("50.00"))
        )
      ))
      .active(false)
      .build();

    Mockito.doReturn(Optional.of(restaurant))
      .when(this.restaurantRepository)
      .findRestaurantInformation(any(Restaurant.class));

    Assertions.assertThatExceptionOfType(OrderDomainException.class)
      .isThrownBy(() -> this.orderApplicationService.createOrder(this.createOrderCommand))
      .withMessage("Restaurant with id %s is currently not active!", RESTAURANT_ID);
  }


}
