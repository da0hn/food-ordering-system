package com.food.ordering.system.payment.service.messaging.listener.kafka;

import com.food.ordering.system.kafka.consumer.KafkaConsumer;
import com.food.ordering.system.kafka.order.avro.model.PaymentOrderStatus;
import com.food.ordering.system.kafka.order.avro.model.PaymentRequestAvroModel;
import com.food.ordering.system.payment.service.domain.ports.api.message.listener.PaymentRequestMessageListener;
import com.food.ordering.system.payment.service.messaging.mapper.PaymentMessagingDataMapper;
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
public class PaymentRequestKafkaListener implements KafkaConsumer<PaymentRequestAvroModel> {

  private final PaymentRequestMessageListener messageListener;
  private final PaymentMessagingDataMapper mapper;

  @Override
  @KafkaListener(
    id = "${kafka-consumer-config.payment-consumer-group-id}",
    topics = "${payment-service.payment-request-topic-name}"
  )
  public void receive(
    @Payload final List<PaymentRequestAvroModel> message,
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
    message.forEach(paymentRequestAvroModel -> {
      if (PaymentOrderStatus.PENDING == paymentRequestAvroModel.getPaymentOrderStatus()) {
        log.info("Processing payment for order id: {}", paymentRequestAvroModel.getOrderId());
        this.messageListener.completePayment(this.mapper.paymentRequestAvroModelToPaymentRequest(paymentRequestAvroModel));
      }
      if (PaymentOrderStatus.CANCELLED == paymentRequestAvroModel.getPaymentOrderStatus()) {
        log.info("Cancelling payment for order id: {}", paymentRequestAvroModel.getOrderId());
        this.messageListener.cancelPayment(this.mapper.paymentRequestAvroModelToPaymentRequest(paymentRequestAvroModel));
      }
    });
  }
}
