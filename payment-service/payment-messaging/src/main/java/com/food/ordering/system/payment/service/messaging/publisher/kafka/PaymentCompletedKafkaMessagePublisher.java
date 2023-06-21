package com.food.ordering.system.payment.service.messaging.publisher.kafka;

import com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel;
import com.food.ordering.system.kafka.producer.KafkaMessageHelper;
import com.food.ordering.system.kafka.producer.service.KafkaProducer;
import com.food.ordering.system.payment.service.domain.events.PaymentCompletedEvent;
import com.food.ordering.system.payment.service.domain.config.PaymentServiceConfigData;
import com.food.ordering.system.payment.service.domain.ports.spi.message.publisher.PaymentCompletedMessagePublisher;
import com.food.ordering.system.payment.service.messaging.mapper.PaymentMessagingDataMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class PaymentCompletedKafkaMessagePublisher implements PaymentCompletedMessagePublisher {

  private final PaymentMessagingDataMapper mapper;
  private final KafkaProducer<String, PaymentResponseAvroModel> kafkaProducer;
  private final PaymentServiceConfigData paymentServiceConfigData;
  private final KafkaMessageHelper kafkaMessageHelper;


  @Override
  public void publish(final PaymentCompletedEvent event) {
    final var orderId = event.getPayment().getOrderId().getValue().toString();
    log.info("Received PaymentCompletedEvent for order id: {}", orderId);
    final var paymentResponseAvroModel = this.mapper.paymentCompletedEventToPaymentResponseAvroModel(event);
    try {
      this.kafkaProducer.send(
        this.paymentServiceConfigData.getPaymentResponseTopicName(),
        orderId,
        paymentResponseAvroModel,
        this.kafkaMessageHelper.makeKafkaCallback(this.paymentServiceConfigData.getPaymentResponseTopicName(), paymentResponseAvroModel, orderId)
      );
      log.info("PaymentResponseAvroModel sent to kafka for order id: {}", orderId);
    } catch (final Exception e) {
      log.error("Error while sending PaymentAvroResponseAvroModel message to kafka for order id: {}, error: {}", orderId, e.getMessage());
    }
  }
}
