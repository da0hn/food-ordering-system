package com.food.ordering.system.order.service.messaging.listener.kafka;

import com.food.ordering.system.kafka.consumer.KafkaConsumer;
import com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel;
import com.food.ordering.system.kafka.order.avro.model.PaymentStatus;
import com.food.ordering.system.order.service.domain.ports.api.message.listener.PaymentResponseMessageListener;
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
public class PaymentResponseKafkaListener implements KafkaConsumer<PaymentResponseAvroModel> {

  private final PaymentResponseMessageListener messageListener;
  private final OrderMessagingDataMapper mapper;


  @Override
  @KafkaListener(id = "${kafka-consumer-config.payment-consumer-group-id}", topics = "${order-service.payment-response-topic-name}")
  public void receive(
    @Payload final List<PaymentResponseAvroModel> message,
    @Header(KafkaHeaders.RECEIVED_KEY) final List<String> keys,
    @Header(KafkaHeaders.RECEIVED_PARTITION) final List<Integer> partitions,
    @Header(KafkaHeaders.OFFSET) final List<Long> offsets
  ) {
    log.info(
      "{} number of payment responses received with keys: {}, partitions: {}, and offsets: {}",
      message.size(),
      keys,
      partitions,
      offsets
    );
    message.forEach(response -> {
      if (PaymentStatus.COMPLETED == response.getPaymentStatus()) {
        log.info("Processing successful payment for order id: {}", response.getOrderId());
        this.messageListener.paymentCompleted(this.mapper.paymentResponseAvroModelToPaymentResponse(response));
      }
      if (PaymentStatus.CANCELLED == response.getPaymentStatus() || PaymentStatus.FAILED == response.getPaymentStatus()) {
        log.info("Processing unsuccessful payment for order id: {}", response.getOrderId());
        this.messageListener.paymentCancelled(this.mapper.paymentResponseAvroModelToPaymentResponse(response));
      }
    });
  }

}
