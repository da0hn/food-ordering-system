package com.food.ordering.system.kafka.producer.service.impl;

import com.food.ordering.system.kafka.producer.exception.KafkaProducerException;
import com.food.ordering.system.kafka.producer.service.KafkaProducer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.PreDestroy;
import java.io.Serializable;

@Slf4j
@Component
@AllArgsConstructor
public class KafkaProducerImpl<K extends Serializable, V extends SpecificRecordBase> implements KafkaProducer<K, V> {

  private final KafkaTemplate<K, V> kafkaTemplate;

  @Override
  public void send(
    final String topicName,
    final K key,
    final V message,
    final ListenableFutureCallback<? super SendResult<K, V>> callback
  ) {
    log.info("Sending message={} to topic={}", message, topicName);
    try {
      final var kafkaResultFuture = this.kafkaTemplate.send(topicName, key, message);
      kafkaResultFuture.addCallback(callback);
    }
    catch (final KafkaException exception) {
      log.error(
        "Error on kafka producer with key: {}, message: {} and exception: {}",
        key,
        message,
        exception.getMessage()
      );
      throw new KafkaProducerException(
        exception,
        "Error on kafka producer with key {0} and message {1}",
        key,
        message
      );
    }
  }


  @PreDestroy
  public void close() {
    if (this.kafkaTemplate != null) {
      log.info("Closing kafka producer!");
      this.kafkaTemplate.destroy();
    }
  }


}
