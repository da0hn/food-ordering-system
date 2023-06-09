package com.food.ordering.system.order.service.messaging.publisher.kafka;

import com.food.ordering.system.domain.event.OrderPaidEvent;
import com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel;
import com.food.ordering.system.kafka.producer.KafkaMessageHelper;
import com.food.ordering.system.kafka.producer.service.KafkaProducer;
import com.food.ordering.system.order.service.domain.configuration.OrderServiceConfigData;
import com.food.ordering.system.order.service.domain.ports.spi.message.publisher.OrderPaidRestaurantRequestMessagePublisher;
import com.food.ordering.system.order.service.messaging.mapper.OrderMessagingDataMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class PayOrderKafkaMessagePublisher implements OrderPaidRestaurantRequestMessagePublisher {

  private final OrderMessagingDataMapper mapper;
  private final OrderServiceConfigData orderServiceConfigData;
  private final KafkaProducer<String, RestaurantApprovalRequestAvroModel> kafkaProducer;
  private final KafkaMessageHelper kafkaMessageHelper;


  @Override
  public void publish(final OrderPaidEvent event) {
    final var orderId = event.getOrder().getId().getValue();
    log.info("Received OrderPaidEvent for order id: {}", orderId);
    try {
      final var message = this.mapper.orderPaidEventToRestaurantApprovalRequestAvroModel(event);
      final var topic = this.orderServiceConfigData.getRestaurantApprovalResponseTopicName();
      this.kafkaProducer.send(
        topic,
        orderId.toString(),
        message,
        this.kafkaMessageHelper.makeKafkaCallback(
          topic,
          message,
          orderId.toString()
        )
      );
      log.info("RestaurantApprovalRequestAvroModel sent to Kafka for order id {}", message.getOrderId());
    }
    catch (Exception e) {
      log.error("Error while sending RestaurantApprovalRequestAvroModel message to kafka with orderId {}, error {}", orderId, e.getMessage());
    }

  }

}
