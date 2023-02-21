package com.food.ordering.system.order.service.messaging.publisher.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
public class OrderKafkaMessageHelper {

  public <T> ListenableFutureCallback<? super SendResult<String, T>> makeKafkaCallback(
    final String responseTopicName,
    final T message,
    final String orderId
  ) {
    return new ListenableFutureCallback<>() {
      @Override
      public void onFailure(final Throwable ex) {
        log.error("Error while sending {} message {} to topic {}", message.getClass().getName(), message, responseTopicName, ex);
      }

      @Override
      public void onSuccess(final SendResult<String, T> result) {
        final RecordMetadata metadata = result.getRecordMetadata();
        log.info(
          "Received successfull resopnse from Kafka for order Id {} | Topic {} | Partition {} | OffSet {} | Timestamp {}",
          orderId,
          metadata.topic(),
          metadata.partition(),
          metadata.offset(),
          metadata.timestamp()
        );
      }
    };
  }

}
