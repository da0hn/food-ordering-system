/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.food.ordering.system.kafka.order.avro.model;

import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.SchemaStore;
import org.apache.avro.specific.SpecificData;

@org.apache.avro.specific.AvroGenerated
public class PaymentResponseAvroModel extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"PaymentResponseAvroModel\",\"namespace\":\"com.food.ordering.system.kafka.order.avro.model\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"sagaId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"paymentId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"customerId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"orderId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"price\",\"type\":{\"type\":\"bytes\",\"logicalType\":\"decimal\",\"precision\":10,\"scale\":2}},{\"name\":\"createdAt\",\"type\":{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}},{\"name\":\"paymentStatus\",\"type\":{\"type\":\"enum\",\"name\":\"PaymentStatus\",\"symbols\":[\"COMPLETED\",\"CANCELLED\",\"FAILED\"]}},{\"name\":\"failureMessages\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}}]}");
  private static final long serialVersionUID = -2126784712017759782L;
  private static final SpecificData MODEL$ = new SpecificData();
  private static final BinaryMessageEncoder<PaymentResponseAvroModel> ENCODER =
      new BinaryMessageEncoder<PaymentResponseAvroModel>(MODEL$, SCHEMA$);
  private static final BinaryMessageDecoder<PaymentResponseAvroModel> DECODER =
      new BinaryMessageDecoder<PaymentResponseAvroModel>(MODEL$, SCHEMA$);
  private static final org.apache.avro.Conversion<?>[] conversions =
      new org.apache.avro.Conversion<?>[] {
      null,
      null,
      null,
      null,
      null,
      new org.apache.avro.Conversions.DecimalConversion(),
      new org.apache.avro.data.TimeConversions.TimestampMillisConversion(),
      null,
      null,
      null
  };
  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<PaymentResponseAvroModel>
    WRITER$ = (org.apache.avro.io.DatumWriter<PaymentResponseAvroModel>)MODEL$.createDatumWriter(SCHEMA$);
  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<PaymentResponseAvroModel>
    READER$ = (org.apache.avro.io.DatumReader<PaymentResponseAvroModel>)MODEL$.createDatumReader(SCHEMA$);

  static {
    MODEL$.addLogicalTypeConversion(new org.apache.avro.data.TimeConversions.TimestampMillisConversion());
    MODEL$.addLogicalTypeConversion(new org.apache.avro.Conversions.DecimalConversion());
  }

  private java.lang.String id;
  private java.lang.String sagaId;
  private java.lang.String paymentId;
  private java.lang.String customerId;
  private java.lang.String orderId;
  private java.math.BigDecimal price;
  private java.time.Instant createdAt;
  private com.food.ordering.system.kafka.order.avro.model.PaymentStatus paymentStatus;
  private java.util.List<java.lang.String> failureMessages;
  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public PaymentResponseAvroModel() {}
  /**
   * All-args constructor.
   * @param id The new value for id
   * @param sagaId The new value for sagaId
   * @param paymentId The new value for paymentId
   * @param customerId The new value for customerId
   * @param orderId The new value for orderId
   * @param price The new value for price
   * @param createdAt The new value for createdAt
   * @param paymentStatus The new value for paymentStatus
   * @param failureMessages The new value for failureMessages
   */
  public PaymentResponseAvroModel(final java.lang.String id, final java.lang.String sagaId, final java.lang.String paymentId, final java.lang.String customerId, final java.lang.String orderId, final java.math.BigDecimal price, final java.time.Instant createdAt, final com.food.ordering.system.kafka.order.avro.model.PaymentStatus paymentStatus, final java.util.List<java.lang.String> failureMessages) {
    this.id = id;
    this.sagaId = sagaId;
    this.paymentId = paymentId;
    this.customerId = customerId;
    this.orderId = orderId;
    this.price = price;
    this.createdAt = createdAt.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
    this.paymentStatus = paymentStatus;
    this.failureMessages = failureMessages;
  }

  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<PaymentResponseAvroModel> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<PaymentResponseAvroModel> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<PaymentResponseAvroModel> createDecoder(final SchemaStore resolver) {
    return new BinaryMessageDecoder<PaymentResponseAvroModel>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Deserializes a PaymentResponseAvroModel from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a PaymentResponseAvroModel instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static PaymentResponseAvroModel fromByteBuffer(
    final java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /**
   * Creates a new PaymentResponseAvroModel RecordBuilder.
   * @return A new PaymentResponseAvroModel RecordBuilder
   */
  public static com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel.Builder newBuilder() {
    return new com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel.Builder();
  }

  /**
   * Creates a new PaymentResponseAvroModel RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new PaymentResponseAvroModel RecordBuilder
   */
  public static com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel.Builder newBuilder(final com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel.Builder other) {
    if (other == null) {
      return new com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel.Builder();
    } else {
      return new com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel.Builder(other);
    }
  }

  /**
   * Creates a new PaymentResponseAvroModel RecordBuilder by copying an existing PaymentResponseAvroModel instance.
   * @param other The existing instance to copy.
   * @return A new PaymentResponseAvroModel RecordBuilder
   */
  public static com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel.Builder newBuilder(final com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel other) {
    if (other == null) {
      return new com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel.Builder();
    } else {
      return new com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel.Builder(other);
    }
  }

  /**
   * Serializes this PaymentResponseAvroModel to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(final int field$) {
    switch (field$) {
    case 0: return this.id;
    case 1: return this.sagaId;
    case 2: return this.paymentId;
    case 3: return this.customerId;
    case 4: return this.orderId;
    case 5: return this.price;
    case 6: return this.createdAt;
    case 7: return this.paymentStatus;
    case 8: return this.failureMessages;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(final int field$, final java.lang.Object value$) {
    switch (field$) {
    case 0:
      this.id = value$ != null ? value$.toString() : null; break;
    case 1:
      this.sagaId = value$ != null ? value$.toString() : null; break;
    case 2:
      this.paymentId = value$ != null ? value$.toString() : null; break;
    case 3:
      this.customerId = value$ != null ? value$.toString() : null; break;
    case 4:
      this.orderId = value$ != null ? value$.toString() : null; break;
    case 5:
      this.price = (java.math.BigDecimal)value$; break;
    case 6:
      this.createdAt = (java.time.Instant)value$; break;
    case 7:
      this.paymentStatus = (com.food.ordering.system.kafka.order.avro.model.PaymentStatus)value$; break;
    case 8:
      this.failureMessages = (java.util.List<java.lang.String>)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Conversion<?> getConversion(final int field) {
    return conversions[field];
  }

  @Override public void writeExternal(final java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @Override public void readExternal(final java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  /**
   * Gets the value of the 'id' field.
   * @return The value of the 'id' field.
   */
  public java.lang.String getId() {
    return this.id;
  }

  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(final java.lang.String value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'sagaId' field.
   * @return The value of the 'sagaId' field.
   */
  public java.lang.String getSagaId() {
    return this.sagaId;
  }

  /**
   * Sets the value of the 'sagaId' field.
   * @param value the value to set.
   */
  public void setSagaId(final java.lang.String value) {
    this.sagaId = value;
  }

  /**
   * Gets the value of the 'paymentId' field.
   * @return The value of the 'paymentId' field.
   */
  public java.lang.String getPaymentId() {
    return this.paymentId;
  }

  /**
   * Sets the value of the 'paymentId' field.
   * @param value the value to set.
   */
  public void setPaymentId(final java.lang.String value) {
    this.paymentId = value;
  }

  /**
   * Gets the value of the 'customerId' field.
   * @return The value of the 'customerId' field.
   */
  public java.lang.String getCustomerId() {
    return this.customerId;
  }

  /**
   * Sets the value of the 'customerId' field.
   * @param value the value to set.
   */
  public void setCustomerId(final java.lang.String value) {
    this.customerId = value;
  }

  /**
   * Gets the value of the 'orderId' field.
   * @return The value of the 'orderId' field.
   */
  public java.lang.String getOrderId() {
    return this.orderId;
  }

  /**
   * Sets the value of the 'orderId' field.
   * @param value the value to set.
   */
  public void setOrderId(final java.lang.String value) {
    this.orderId = value;
  }

  /**
   * Gets the value of the 'price' field.
   * @return The value of the 'price' field.
   */
  public java.math.BigDecimal getPrice() {
    return this.price;
  }

  /**
   * Sets the value of the 'price' field.
   * @param value the value to set.
   */
  public void setPrice(final java.math.BigDecimal value) {
    this.price = value;
  }

  /**
   * Gets the value of the 'createdAt' field.
   * @return The value of the 'createdAt' field.
   */
  public java.time.Instant getCreatedAt() {
    return this.createdAt;
  }

  /**
   * Sets the value of the 'createdAt' field.
   * @param value the value to set.
   */
  public void setCreatedAt(final java.time.Instant value) {
    this.createdAt = value.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
  }

  /**
   * Gets the value of the 'paymentStatus' field.
   * @return The value of the 'paymentStatus' field.
   */
  public com.food.ordering.system.kafka.order.avro.model.PaymentStatus getPaymentStatus() {
    return this.paymentStatus;
  }

  /**
   * Sets the value of the 'paymentStatus' field.
   * @param value the value to set.
   */
  public void setPaymentStatus(final com.food.ordering.system.kafka.order.avro.model.PaymentStatus value) {
    this.paymentStatus = value;
  }

  /**
   * Gets the value of the 'failureMessages' field.
   * @return The value of the 'failureMessages' field.
   */
  public java.util.List<java.lang.String> getFailureMessages() {
    return this.failureMessages;
  }

  /**
   * Sets the value of the 'failureMessages' field.
   * @param value the value to set.
   */
  public void setFailureMessages(final java.util.List<java.lang.String> value) {
    this.failureMessages = value;
  }

  /**
   * RecordBuilder for PaymentResponseAvroModel instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<PaymentResponseAvroModel>
    implements org.apache.avro.data.RecordBuilder<PaymentResponseAvroModel> {

    private java.lang.String id;
    private java.lang.String sagaId;
    private java.lang.String paymentId;
    private java.lang.String customerId;
    private java.lang.String orderId;
    private java.math.BigDecimal price;
    private java.time.Instant createdAt;
    private com.food.ordering.system.kafka.order.avro.model.PaymentStatus paymentStatus;
    private java.util.List<java.lang.String> failureMessages;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(final com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel.Builder other) {
      super(other);
      if (isValidValue(this.fields()[0], other.id)) {
        this.id = this.data().deepCopy(this.fields()[0].schema(), other.id);
        this.fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(this.fields()[1], other.sagaId)) {
        this.sagaId = this.data().deepCopy(this.fields()[1].schema(), other.sagaId);
        this.fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(this.fields()[2], other.paymentId)) {
        this.paymentId = this.data().deepCopy(this.fields()[2].schema(), other.paymentId);
        this.fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(this.fields()[3], other.customerId)) {
        this.customerId = this.data().deepCopy(this.fields()[3].schema(), other.customerId);
        this.fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(this.fields()[4], other.orderId)) {
        this.orderId = this.data().deepCopy(this.fields()[4].schema(), other.orderId);
        this.fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(this.fields()[5], other.price)) {
        this.price = this.data().deepCopy(this.fields()[5].schema(), other.price);
        this.fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(this.fields()[6], other.createdAt)) {
        this.createdAt = this.data().deepCopy(this.fields()[6].schema(), other.createdAt);
        this.fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
      if (isValidValue(this.fields()[7], other.paymentStatus)) {
        this.paymentStatus = this.data().deepCopy(this.fields()[7].schema(), other.paymentStatus);
        this.fieldSetFlags()[7] = other.fieldSetFlags()[7];
      }
      if (isValidValue(this.fields()[8], other.failureMessages)) {
        this.failureMessages = this.data().deepCopy(this.fields()[8].schema(), other.failureMessages);
        this.fieldSetFlags()[8] = other.fieldSetFlags()[8];
      }
    }

    /**
     * Creates a Builder by copying an existing PaymentResponseAvroModel instance
     * @param other The existing instance to copy.
     */
    private Builder(final com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(this.fields()[0], other.id)) {
        this.id = this.data().deepCopy(this.fields()[0].schema(), other.id);
        this.fieldSetFlags()[0] = true;
      }
      if (isValidValue(this.fields()[1], other.sagaId)) {
        this.sagaId = this.data().deepCopy(this.fields()[1].schema(), other.sagaId);
        this.fieldSetFlags()[1] = true;
      }
      if (isValidValue(this.fields()[2], other.paymentId)) {
        this.paymentId = this.data().deepCopy(this.fields()[2].schema(), other.paymentId);
        this.fieldSetFlags()[2] = true;
      }
      if (isValidValue(this.fields()[3], other.customerId)) {
        this.customerId = this.data().deepCopy(this.fields()[3].schema(), other.customerId);
        this.fieldSetFlags()[3] = true;
      }
      if (isValidValue(this.fields()[4], other.orderId)) {
        this.orderId = this.data().deepCopy(this.fields()[4].schema(), other.orderId);
        this.fieldSetFlags()[4] = true;
      }
      if (isValidValue(this.fields()[5], other.price)) {
        this.price = this.data().deepCopy(this.fields()[5].schema(), other.price);
        this.fieldSetFlags()[5] = true;
      }
      if (isValidValue(this.fields()[6], other.createdAt)) {
        this.createdAt = this.data().deepCopy(this.fields()[6].schema(), other.createdAt);
        this.fieldSetFlags()[6] = true;
      }
      if (isValidValue(this.fields()[7], other.paymentStatus)) {
        this.paymentStatus = this.data().deepCopy(this.fields()[7].schema(), other.paymentStatus);
        this.fieldSetFlags()[7] = true;
      }
      if (isValidValue(this.fields()[8], other.failureMessages)) {
        this.failureMessages = this.data().deepCopy(this.fields()[8].schema(), other.failureMessages);
        this.fieldSetFlags()[8] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public java.lang.String getId() {
      return this.id;
    }


    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel.Builder setId(final java.lang.String value) {
      this.validate(this.fields()[0], value);
      this.id = value;
      this.fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return this.fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel.Builder clearId() {
      this.id = null;
      this.fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'sagaId' field.
      * @return The value.
      */
    public java.lang.String getSagaId() {
      return this.sagaId;
    }


    /**
      * Sets the value of the 'sagaId' field.
      * @param value The value of 'sagaId'.
      * @return This builder.
      */
    public com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel.Builder setSagaId(final java.lang.String value) {
      this.validate(this.fields()[1], value);
      this.sagaId = value;
      this.fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'sagaId' field has been set.
      * @return True if the 'sagaId' field has been set, false otherwise.
      */
    public boolean hasSagaId() {
      return this.fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'sagaId' field.
      * @return This builder.
      */
    public com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel.Builder clearSagaId() {
      this.sagaId = null;
      this.fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'paymentId' field.
      * @return The value.
      */
    public java.lang.String getPaymentId() {
      return this.paymentId;
    }


    /**
      * Sets the value of the 'paymentId' field.
      * @param value The value of 'paymentId'.
      * @return This builder.
      */
    public com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel.Builder setPaymentId(final java.lang.String value) {
      this.validate(this.fields()[2], value);
      this.paymentId = value;
      this.fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'paymentId' field has been set.
      * @return True if the 'paymentId' field has been set, false otherwise.
      */
    public boolean hasPaymentId() {
      return this.fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'paymentId' field.
      * @return This builder.
      */
    public com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel.Builder clearPaymentId() {
      this.paymentId = null;
      this.fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'customerId' field.
      * @return The value.
      */
    public java.lang.String getCustomerId() {
      return this.customerId;
    }


    /**
      * Sets the value of the 'customerId' field.
      * @param value The value of 'customerId'.
      * @return This builder.
      */
    public com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel.Builder setCustomerId(final java.lang.String value) {
      this.validate(this.fields()[3], value);
      this.customerId = value;
      this.fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'customerId' field has been set.
      * @return True if the 'customerId' field has been set, false otherwise.
      */
    public boolean hasCustomerId() {
      return this.fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'customerId' field.
      * @return This builder.
      */
    public com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel.Builder clearCustomerId() {
      this.customerId = null;
      this.fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'orderId' field.
      * @return The value.
      */
    public java.lang.String getOrderId() {
      return this.orderId;
    }


    /**
      * Sets the value of the 'orderId' field.
      * @param value The value of 'orderId'.
      * @return This builder.
      */
    public com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel.Builder setOrderId(final java.lang.String value) {
      this.validate(this.fields()[4], value);
      this.orderId = value;
      this.fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'orderId' field has been set.
      * @return True if the 'orderId' field has been set, false otherwise.
      */
    public boolean hasOrderId() {
      return this.fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'orderId' field.
      * @return This builder.
      */
    public com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel.Builder clearOrderId() {
      this.orderId = null;
      this.fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'price' field.
      * @return The value.
      */
    public java.math.BigDecimal getPrice() {
      return this.price;
    }


    /**
      * Sets the value of the 'price' field.
      * @param value The value of 'price'.
      * @return This builder.
      */
    public com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel.Builder setPrice(final java.math.BigDecimal value) {
      this.validate(this.fields()[5], value);
      this.price = value;
      this.fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'price' field has been set.
      * @return True if the 'price' field has been set, false otherwise.
      */
    public boolean hasPrice() {
      return this.fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'price' field.
      * @return This builder.
      */
    public com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel.Builder clearPrice() {
      this.price = null;
      this.fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'createdAt' field.
      * @return The value.
      */
    public java.time.Instant getCreatedAt() {
      return this.createdAt;
    }


    /**
      * Sets the value of the 'createdAt' field.
      * @param value The value of 'createdAt'.
      * @return This builder.
      */
    public com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel.Builder setCreatedAt(final java.time.Instant value) {
      this.validate(this.fields()[6], value);
      this.createdAt = value.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
      this.fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'createdAt' field has been set.
      * @return True if the 'createdAt' field has been set, false otherwise.
      */
    public boolean hasCreatedAt() {
      return this.fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'createdAt' field.
      * @return This builder.
      */
    public com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel.Builder clearCreatedAt() {
      this.fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'paymentStatus' field.
      * @return The value.
      */
    public com.food.ordering.system.kafka.order.avro.model.PaymentStatus getPaymentStatus() {
      return this.paymentStatus;
    }


    /**
      * Sets the value of the 'paymentStatus' field.
      * @param value The value of 'paymentStatus'.
      * @return This builder.
      */
    public com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel.Builder setPaymentStatus(final com.food.ordering.system.kafka.order.avro.model.PaymentStatus value) {
      this.validate(this.fields()[7], value);
      this.paymentStatus = value;
      this.fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'paymentStatus' field has been set.
      * @return True if the 'paymentStatus' field has been set, false otherwise.
      */
    public boolean hasPaymentStatus() {
      return this.fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'paymentStatus' field.
      * @return This builder.
      */
    public com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel.Builder clearPaymentStatus() {
      this.paymentStatus = null;
      this.fieldSetFlags()[7] = false;
      return this;
    }

    /**
      * Gets the value of the 'failureMessages' field.
      * @return The value.
      */
    public java.util.List<java.lang.String> getFailureMessages() {
      return this.failureMessages;
    }


    /**
      * Sets the value of the 'failureMessages' field.
      * @param value The value of 'failureMessages'.
      * @return This builder.
      */
    public com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel.Builder setFailureMessages(final java.util.List<java.lang.String> value) {
      this.validate(this.fields()[8], value);
      this.failureMessages = value;
      this.fieldSetFlags()[8] = true;
      return this;
    }

    /**
      * Checks whether the 'failureMessages' field has been set.
      * @return True if the 'failureMessages' field has been set, false otherwise.
      */
    public boolean hasFailureMessages() {
      return this.fieldSetFlags()[8];
    }


    /**
      * Clears the value of the 'failureMessages' field.
      * @return This builder.
      */
    public com.food.ordering.system.kafka.order.avro.model.PaymentResponseAvroModel.Builder clearFailureMessages() {
      this.failureMessages = null;
      this.fieldSetFlags()[8] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PaymentResponseAvroModel build() {
      try {
        final PaymentResponseAvroModel record = new PaymentResponseAvroModel();
        record.id = this.fieldSetFlags()[0] ? this.id : (java.lang.String) this.defaultValue(this.fields()[0]);
        record.sagaId = this.fieldSetFlags()[1] ? this.sagaId : (java.lang.String) this.defaultValue(this.fields()[1]);
        record.paymentId = this.fieldSetFlags()[2] ? this.paymentId : (java.lang.String) this.defaultValue(this.fields()[2]);
        record.customerId = this.fieldSetFlags()[3] ? this.customerId : (java.lang.String) this.defaultValue(this.fields()[3]);
        record.orderId = this.fieldSetFlags()[4] ? this.orderId : (java.lang.String) this.defaultValue(this.fields()[4]);
        record.price = this.fieldSetFlags()[5] ? this.price : (java.math.BigDecimal) this.defaultValue(this.fields()[5]);
        record.createdAt = this.fieldSetFlags()[6] ? this.createdAt : (java.time.Instant) this.defaultValue(this.fields()[6]);
        record.paymentStatus = this.fieldSetFlags()[7] ? this.paymentStatus : (com.food.ordering.system.kafka.order.avro.model.PaymentStatus) this.defaultValue(this.fields()[7]);
        record.failureMessages = this.fieldSetFlags()[8] ? this.failureMessages : (java.util.List<java.lang.String>) this.defaultValue(this.fields()[8]);
        return record;
      } catch (final org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (final java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

}










