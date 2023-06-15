package com.food.ordering.system.restaurant.service.messaging.publisher.kafka;

import com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalResponseAvroModel;
import com.food.ordering.system.kafka.producer.KafkaMessageHelper;
import com.food.ordering.system.kafka.producer.service.KafkaProducer;
import com.food.ordering.system.restaurant.service.domain.config.RestaurantServiceConfigData;
import com.food.ordering.system.restaurant.service.domain.events.OrderApprovedEvent;
import com.food.ordering.system.restaurant.service.domain.events.OrderRejectedEvent;
import com.food.ordering.system.restaurant.service.domain.ports.spi.message.publisher.OrderApprovedMessagePublisher;
import com.food.ordering.system.restaurant.service.domain.ports.spi.message.publisher.OrderRejectedMessagePublisher;
import com.food.ordering.system.restaurant.service.messaging.mapper.RestaurantMessagingDataMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class OrderRejectedKafkaMessagePublisher implements OrderRejectedMessagePublisher {

  private final RestaurantMessagingDataMapper restaurantMessagingDataMapper;
  private final KafkaProducer<String, RestaurantApprovalResponseAvroModel> kafkaProducer;
  private final RestaurantServiceConfigData restaurantServiceConfigData;
  private final KafkaMessageHelper kafkaMessageHelper;

  @Override
  public void publish(final OrderRejectedEvent event) {
    final String orderId = event.getOrderApproval().getOrderId().getValue().toString();
    log.info("Received OrderRejectedEvent for order id: {}", orderId);

    try {
      final var restaurantApprovalResponseAvroModel = this.restaurantMessagingDataMapper.orderRejectedEventToRestaurantApprovalResponseAvroModel(event);

      this.kafkaProducer.send(
        this.restaurantServiceConfigData.getRestaurantApprovalResponseTopicName(),
        orderId,
        restaurantApprovalResponseAvroModel,
        this.kafkaMessageHelper.makeKafkaCallback(this.restaurantServiceConfigData.getRestaurantApprovalResponseTopicName(), restaurantApprovalResponseAvroModel, orderId)
      );

      log.info("RestaurantApprovalResponseAvroModel: sent to kafka at: {}", System.nanoTime());

    } catch (final Exception e) {
      log.error("Error while sending RestaurantApprovalResponseAvroModel message to kafka with order id: {}, error: {}", e, e.getMessage());
    }

  }
}

