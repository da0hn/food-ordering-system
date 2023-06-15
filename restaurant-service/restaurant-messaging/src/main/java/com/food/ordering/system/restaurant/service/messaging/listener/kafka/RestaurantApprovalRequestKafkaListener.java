package com.food.ordering.system.restaurant.service.messaging.listener.kafka;

import com.food.ordering.system.kafka.consumer.KafkaConsumer;
import com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel;
import com.food.ordering.system.restaurant.service.domain.ports.api.message.listener.RestaurantApprovalRequestMessageListener;
import com.food.ordering.system.restaurant.service.messaging.mapper.RestaurantMessagingDataMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class RestaurantApprovalRequestKafkaListener implements KafkaConsumer<RestaurantApprovalRequestAvroModel> {

  private final RestaurantApprovalRequestMessageListener restaurantApprovalRequestMessageListener;
  private final RestaurantMessagingDataMapper restaurantMessagingDataMapper;

  @Override
  @KafkaListener(
    id = "${kafka-consumer-config.restaurant-approval-consumer-group-id}",
    topics = "${restaurant-service.restaurant-approval-request-topic-name}"
  )
  public void receive(
    @Payload final List<RestaurantApprovalRequestAvroModel> message,
    @Header(KafkaHeaders.RECEIVED_KEY) final List<String> keys,
    @Header(KafkaHeaders.RECEIVED_PARTITION) final List<Integer> partitions,
    @Header(KafkaHeaders.OFFSET) final List<Long> offsets
  ) {
    log.info(
      "{} number of orders approval request received with keys {}, partitions {} and offsets {}, sending for restaurant approval",
      message.size(),
      keys.toString(),
      partitions.toString(),
      offsets.toString()
    );

    message.forEach(request -> {
      log.info("Processing order approval for order id: {}", request.getOrderId());
      this.restaurantApprovalRequestMessageListener.approveOrder(
        this.restaurantMessagingDataMapper.restaurantApprovalRequestAvroModelToRestaurantApproval(request)
      );
    });

  }
}
