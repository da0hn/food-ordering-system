package com.food.ordering.system.kafka.producer;

import com.food.ordering.system.kafka.config.data.KafkaConfigData;
import com.food.ordering.system.kafka.config.data.KafkaProducerConfigData;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig<K extends Serializable, V extends SpecificRecordBase> {

  private final KafkaConfigData kafkaConfigData;
  private final KafkaProducerConfigData kafkaProducerConfigData;

  @Autowired
  public KafkaProducerConfig(
    final KafkaConfigData kafkaConfigData,
    final KafkaProducerConfigData kafkaProducerConfigData
  ) {
    this.kafkaConfigData = kafkaConfigData;
    this.kafkaProducerConfigData = kafkaProducerConfigData;
  }

  @Bean
  public KafkaTemplate<K, V> kafkaTemplate() {
    return new KafkaTemplate<>(this.producerFactory());
  }

  @Bean
  public ProducerFactory<K, V> producerFactory() {
    return new DefaultKafkaProducerFactory<>(this.producerConfig());
  }

  @Bean
  public Map<String, Object> producerConfig() {
    final var properties = new HashMap<String, Object>();
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.kafkaConfigData.getBootstrapServers());
    properties.put(this.kafkaConfigData.getSchemaRegistryUrlKey(), this.kafkaConfigData.getSchemaRegistryUrl());
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, this.kafkaProducerConfigData.getKeySerializerClass());
    properties.put(
      ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
      this.kafkaProducerConfigData.getValueSerializerClass()
    );
    properties.put(
      ProducerConfig.BATCH_SIZE_CONFIG,
      this.kafkaProducerConfigData.getBatchSize() * this.kafkaProducerConfigData.getBatchSizeBoostFactor()
    );
    properties.put(ProducerConfig.LINGER_MS_CONFIG, this.kafkaProducerConfigData.getLingerMs());
    properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, this.kafkaProducerConfigData.getCompressionType());
    properties.put(ProducerConfig.ACKS_CONFIG, this.kafkaProducerConfigData.getAcks());
    properties.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, this.kafkaProducerConfigData.getRequestTimeoutMs());
    properties.put(ProducerConfig.RETRIES_CONFIG, this.kafkaProducerConfigData.getRetryCount());
    return properties;
  }


}
