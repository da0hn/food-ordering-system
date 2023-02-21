package com.food.ordering.system.order.service.messaging.publisher.kafka;

import com.food.ordering.system.domain.event.OrderCreatedEvent;
import com.food.ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import com.food.ordering.system.kafka.producer.service.KafkaProducer;
import com.food.ordering.system.order.service.domain.configuration.OrderServiceConfigData;
import com.food.ordering.system.order.service.domain.ports.spi.message.publisher.OrderCreatedPaymentRequestMessagePublisher;
import com.food.ordering.system.order.service.messaging.mapper.OrderMessagingDataMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class CreateOrderKafkaMessagePublisher implements OrderCreatedPaymentRequestMessagePublisher {

  private final OrderMessagingDataMapper mapper;
  private final OrderServiceConfigData orderServiceConfigData;
  private final KafkaProducer<String, PaymentRequestAvroModel> kafkaProducer;
  private final OrderKafkaMessageHelper orderKafkaMessageHelper;


  @Override
  public void publish(final OrderCreatedEvent event) {
    final var orderId = event.getOrder().getId().getValue();
    log.info("Received OrderCreatedEvent for order id: {}", orderId);
    try {
      final var message = this.mapper.orderCreatedEventToPaymentRequestAvroModel(event);
      this.kafkaProducer.send(
        this.orderServiceConfigData.getPaymentRequestTopicName(),
        orderId.toString(),
        message,
        this.orderKafkaMessageHelper.makeKafkaCallback(
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
