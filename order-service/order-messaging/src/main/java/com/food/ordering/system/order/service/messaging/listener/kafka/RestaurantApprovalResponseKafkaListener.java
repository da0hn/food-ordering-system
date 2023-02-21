package com.food.ordering.system.order.service.messaging.listener.kafka;

import com.food.ordering.system.domain.entity.Order;
import com.food.ordering.system.kafka.consumer.KafkaConsumer;
import com.food.ordering.system.kafka.order.avro.model.OrderApprovalStatus;
import com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalResponseAvroModel;
import com.food.ordering.system.order.service.domain.ports.api.message.listener.RestaurantApprovalResponseMessageListener;
import com.food.ordering.system.order.service.messaging.mapper.OrderMessagingDataMapper;
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
public class RestaurantApprovalResponseKafkaListener implements KafkaConsumer<RestaurantApprovalResponseAvroModel> {

  private final RestaurantApprovalResponseMessageListener messageListener;
  private final OrderMessagingDataMapper mapper;


  @Override
  @KafkaListener(
    id = "${kafka-consumer-config.restaurant-approval-consumer-group-id}",
    topics = "${order-service.restaurant-approval-response-topic-name}"
  )
  public void receive(
    @Payload final List<RestaurantApprovalResponseAvroModel> message,
    @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) final List<String> keys,
    @Header(KafkaHeaders.RECEIVED_PARTITION_ID) final List<Integer> partitions,
    @Header(KafkaHeaders.OFFSET) final List<Long> offsets
  ) {
    log.info(
      "{} number of restaurant approval responses received with keys: {}, partitions: {}, and offsets: {}",
      message.size(),
      keys,
      partitions,
      offsets
    );
    message.forEach(response -> {
      if (OrderApprovalStatus.APPROVED == response.getOrderApprovalStatus()) {
        log.info("Processing approved order for order id: {}", response.getOrderId());
        this.messageListener.orderApproved(this.mapper.restaurantApprovalResponseAvroModelToRestaurantApprovalResponse(response));
      }
      if (OrderApprovalStatus.REJECTED == response.getOrderApprovalStatus()) {
        log.info(
          "Processing rejected order for order id: {}, with failure messages: {}",
          response.getOrderId(),
          String.join(Order.FAILURE_MESSAGE_DELIMITER, response.getFailureMessages())
        );
        this.messageListener.orderRejected(this.mapper.restaurantApprovalResponseAvroModelToRestaurantApprovalResponse(response));
      }
    });
  }

}
