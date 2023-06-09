package com.food.ordering.system.order.service.messaging.publisher.kafka;

import com.food.ordering.system.domain.event.OrderCancelledEvent;
import com.food.ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import com.food.ordering.system.kafka.producer.KafkaMessageHelper;
import com.food.ordering.system.kafka.producer.service.KafkaProducer;
import com.food.ordering.system.order.service.domain.configuration.OrderServiceConfigData;
import com.food.ordering.system.order.service.domain.ports.spi.message.publisher.OrderCancelledPaymentRequestMessagePublisher;
import com.food.ordering.system.order.service.messaging.mapper.OrderMessagingDataMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class CancelOrderKafkaMessagePublisher implements OrderCancelledPaymentRequestMessagePublisher {


  private final OrderMessagingDataMapper mapper;
  private final OrderServiceConfigData orderServiceConfigData;
  private final KafkaProducer<String, PaymentRequestAvroModel> kafkaProducer;
  private final KafkaMessageHelper kafkaMessageHelper;

  @Override
  public void publish(final OrderCancelledEvent event) {
    final var orderId = event.getOrder().getId().getValue();
    log.info("Received OrderCancelledEvent for order id: {}", orderId);
    try {
      final var message = this.mapper.orderCancelledEventToPaymentRequestAvroModel(event);
      this.kafkaProducer.send(
        this.orderServiceConfigData.getPaymentRequestTopicName(),
        orderId.toString(),
        message,
        this.kafkaMessageHelper.makeKafkaCallback(
          this.orderServiceConfigData.getPaymentResponseTopicName(),
          message,
          orderId.toString()
        )
      );

      log.info("PaymentRequestAvroModel sent to Kafka for order id {}", message.getOrderId());
    }
    catch (final Exception e) {
      log.error("Error while sending PaymentRequestAvroModel message to kafka with orderId {}, error {}", orderId, e.getMessage());
    }
  }

}
