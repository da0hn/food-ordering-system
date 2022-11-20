package com.food.ordering.system.kafka.consumer.config;

import com.food.ordering.system.kafka.config.data.KafkaConfigData;
import com.food.ordering.system.kafka.config.data.KafkaConsumerConfigData;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig<K extends Serializable, V extends SpecificRecordBase> {

  private final KafkaConfigData kafkaConfigData;
  private final KafkaConsumerConfigData kafkaConsumerConfigData;

  public KafkaConsumerConfig(
    final KafkaConfigData kafkaConfigData,
    final KafkaConsumerConfigData kafkaConsumerConfigData
  ) {
    this.kafkaConfigData = kafkaConfigData;
    this.kafkaConsumerConfigData = kafkaConsumerConfigData;
  }

  @Bean
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<K, V>> kafkaListenerContainerFactory() {
    final ConcurrentKafkaListenerContainerFactory<K, V> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(this.consumerFactory());
    factory.setBatchListener(this.kafkaConsumerConfigData.getBatchListener());
    factory.setConcurrency(this.kafkaConsumerConfigData.getConcurrencyLevel());
    factory.setAutoStartup(this.kafkaConsumerConfigData.getAutoStartup());
    factory.getContainerProperties().setPollTimeout(this.kafkaConsumerConfigData.getPollTimeoutMs());
    return factory;
  }

  @Bean
  public ConsumerFactory<K, V> consumerFactory() {
    return new DefaultKafkaConsumerFactory<>(this.consumerConfigs());
  }

  @Bean
  public Map<String, Object> consumerConfigs() {
    final var properties = new HashMap<String, Object>();

    properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.kafkaConfigData.getBootstrapServers());
    properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, this.kafkaConsumerConfigData.getKeyDeserializer());
    properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, this.kafkaConsumerConfigData.getValueDeserializer());
    properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, this.kafkaConsumerConfigData.getAutoOffsetReset());
    properties.put(this.kafkaConfigData.getSchemaRegistryUrlKey(), this.kafkaConfigData.getSchemaRegistryUrl());
    properties.put(
      this.kafkaConsumerConfigData.getSpecificAvroReaderKey(),
      this.kafkaConsumerConfigData.getSpecificAvroReader()
    );
    properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, this.kafkaConsumerConfigData.getSessionTimeoutMs());
    properties.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, this.kafkaConsumerConfigData.getHeartbeatIntervalMs());
    properties.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, this.kafkaConsumerConfigData.getMaxPollIntervalMs());
    properties.put(
      ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG,
      this.kafkaConsumerConfigData.getMaxPartitionFetchBytesDefault() * this.kafkaConsumerConfigData.getMaxPartitionFetchBytesBoostFactor()
    );
    properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, this.kafkaConsumerConfigData.getMaxPollRecords());
    return properties;
  }


}
