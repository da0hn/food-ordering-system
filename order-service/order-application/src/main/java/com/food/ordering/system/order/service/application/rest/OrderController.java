package com.food.ordering.system.order.service.application.rest;

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse;
import com.food.ordering.system.order.service.domain.ports.api.service.OrderApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/orders", produces = "application/vnd.api.v1+json")
public class OrderController {

  private final OrderApplicationService orderApplicationService;

  public OrderController(final OrderApplicationService orderApplicationService) {
    this.orderApplicationService = orderApplicationService;
  }

  @PostMapping
  public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody final CreateOrderCommand command) {
    log.info("Creating order for customer {} at restaurant {}", command.getCustomerId(), command.getRestaurantId());
    final var response = this.orderApplicationService.createOrder(command);
    log.info("Order created with tracking id {}", response.getOrderTrackingId());
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{trackingId}")
  public ResponseEntity<TrackOrderResponse> getOrderByTrackingId(@PathVariable final UUID trackingId) {
    final var response = this.orderApplicationService.trackOrder(
      TrackOrderQuery.builder().orderTrackingId(trackingId).build()
    );
    log.info("Returning order status with tracking id {}", response.getOrderTrackingId());
    return ResponseEntity.ok(response);
  }


}
