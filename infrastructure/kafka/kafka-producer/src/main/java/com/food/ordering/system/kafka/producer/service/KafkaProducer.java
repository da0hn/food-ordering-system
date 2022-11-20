package com.food.ordering.system.kafka.producer.service;

import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.io.Serializable;

@FunctionalInterface
public interface KafkaProducer<K extends Serializable, V extends SpecificRecordBase> {

  void send(
    final String topicName,
    final K key,
    final V message,
    final ListenableFutureCallback<? super SendResult<K, V>> callback
  );

}
