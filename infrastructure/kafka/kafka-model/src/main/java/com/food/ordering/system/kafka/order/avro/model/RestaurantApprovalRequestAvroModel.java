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
public class RestaurantApprovalRequestAvroModel extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"RestaurantApprovalRequestAvroModel\",\"namespace\":\"com.food.ordering.system.kafka.order.avro.model\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"sagaId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"restaurantId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"orderId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"restaurantOrderStatus\",\"type\":{\"type\":\"enum\",\"name\":\"RestaurantOrderStatus\",\"symbols\":[\"PAID\"]}},{\"name\":\"products\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Product\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"},\"logicalType\":\"uuid\"},{\"name\":\"quantity\",\"type\":\"int\"}]}}},{\"name\":\"price\",\"type\":{\"type\":\"bytes\",\"logicalType\":\"decimal\",\"precision\":10,\"scale\":2}},{\"name\":\"createdAt\",\"type\":{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}}]}");
  private static final long serialVersionUID = -3917361261016430486L;
  private static final SpecificData MODEL$ = new SpecificData();
  private static final BinaryMessageEncoder<RestaurantApprovalRequestAvroModel> ENCODER =
      new BinaryMessageEncoder<RestaurantApprovalRequestAvroModel>(MODEL$, SCHEMA$);
  private static final BinaryMessageDecoder<RestaurantApprovalRequestAvroModel> DECODER =
      new BinaryMessageDecoder<RestaurantApprovalRequestAvroModel>(MODEL$, SCHEMA$);
  private static final org.apache.avro.Conversion<?>[] conversions =
      new org.apache.avro.Conversion<?>[] {
      null,
      null,
      null,
      null,
      null,
      null,
      new org.apache.avro.Conversions.DecimalConversion(),
      new org.apache.avro.data.TimeConversions.TimestampMillisConversion(),
      null
  };
  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<RestaurantApprovalRequestAvroModel>
    WRITER$ = (org.apache.avro.io.DatumWriter<RestaurantApprovalRequestAvroModel>)MODEL$.createDatumWriter(SCHEMA$);
  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<RestaurantApprovalRequestAvroModel>
    READER$ = (org.apache.avro.io.DatumReader<RestaurantApprovalRequestAvroModel>)MODEL$.createDatumReader(SCHEMA$);

  static {
    MODEL$.addLogicalTypeConversion(new org.apache.avro.data.TimeConversions.TimestampMillisConversion());
    MODEL$.addLogicalTypeConversion(new org.apache.avro.Conversions.DecimalConversion());
  }

  private java.lang.String id;
  private java.lang.String sagaId;
  private java.lang.String restaurantId;
  private java.lang.String orderId;
  private com.food.ordering.system.kafka.order.avro.model.RestaurantOrderStatus restaurantOrderStatus;
  private java.util.List<com.food.ordering.system.kafka.order.avro.model.Product> products;
  private java.math.BigDecimal price;
  private java.time.Instant createdAt;
  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public RestaurantApprovalRequestAvroModel() {}
  /**
   * All-args constructor.
   * @param id The new value for id
   * @param sagaId The new value for sagaId
   * @param restaurantId The new value for restaurantId
   * @param orderId The new value for orderId
   * @param restaurantOrderStatus The new value for restaurantOrderStatus
   * @param products The new value for products
   * @param price The new value for price
   * @param createdAt The new value for createdAt
   */
  public RestaurantApprovalRequestAvroModel(final java.lang.String id, final java.lang.String sagaId, final java.lang.String restaurantId, final java.lang.String orderId, final com.food.ordering.system.kafka.order.avro.model.RestaurantOrderStatus restaurantOrderStatus, final java.util.List<com.food.ordering.system.kafka.order.avro.model.Product> products, final java.math.BigDecimal price, final java.time.Instant createdAt) {
    this.id = id;
    this.sagaId = sagaId;
    this.restaurantId = restaurantId;
    this.orderId = orderId;
    this.restaurantOrderStatus = restaurantOrderStatus;
    this.products = products;
    this.price = price;
    this.createdAt = createdAt.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
  }

  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<RestaurantApprovalRequestAvroModel> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<RestaurantApprovalRequestAvroModel> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<RestaurantApprovalRequestAvroModel> createDecoder(final SchemaStore resolver) {
    return new BinaryMessageDecoder<RestaurantApprovalRequestAvroModel>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Deserializes a RestaurantApprovalRequestAvroModel from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a RestaurantApprovalRequestAvroModel instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static RestaurantApprovalRequestAvroModel fromByteBuffer(
    final java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /**
   * Creates a new RestaurantApprovalRequestAvroModel RecordBuilder.
   * @return A new RestaurantApprovalRequestAvroModel RecordBuilder
   */
  public static com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder newBuilder() {
    return new com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder();
  }

  /**
   * Creates a new RestaurantApprovalRequestAvroModel RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new RestaurantApprovalRequestAvroModel RecordBuilder
   */
  public static com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder newBuilder(final com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder other) {
    if (other == null) {
      return new com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder();
    } else {
      return new com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder(other);
    }
  }

  /**
   * Creates a new RestaurantApprovalRequestAvroModel RecordBuilder by copying an existing RestaurantApprovalRequestAvroModel instance.
   * @param other The existing instance to copy.
   * @return A new RestaurantApprovalRequestAvroModel RecordBuilder
   */
  public static com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder newBuilder(final com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel other) {
    if (other == null) {
      return new com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder();
    } else {
      return new com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder(other);
    }
  }

  /**
   * Serializes this RestaurantApprovalRequestAvroModel to a ByteBuffer.
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
    case 2: return this.restaurantId;
    case 3: return this.orderId;
    case 4: return this.restaurantOrderStatus;
    case 5: return this.products;
    case 6: return this.price;
    case 7: return this.createdAt;
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
      this.restaurantId = value$ != null ? value$.toString() : null; break;
    case 3:
      this.orderId = value$ != null ? value$.toString() : null; break;
    case 4:
      this.restaurantOrderStatus = (com.food.ordering.system.kafka.order.avro.model.RestaurantOrderStatus)value$; break;
    case 5:
      this.products = (java.util.List<com.food.ordering.system.kafka.order.avro.model.Product>)value$; break;
    case 6:
      this.price = (java.math.BigDecimal)value$; break;
    case 7:
      this.createdAt = (java.time.Instant)value$; break;
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
   * Gets the value of the 'restaurantId' field.
   * @return The value of the 'restaurantId' field.
   */
  public java.lang.String getRestaurantId() {
    return this.restaurantId;
  }

  /**
   * Sets the value of the 'restaurantId' field.
   * @param value the value to set.
   */
  public void setRestaurantId(final java.lang.String value) {
    this.restaurantId = value;
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
   * Gets the value of the 'restaurantOrderStatus' field.
   * @return The value of the 'restaurantOrderStatus' field.
   */
  public com.food.ordering.system.kafka.order.avro.model.RestaurantOrderStatus getRestaurantOrderStatus() {
    return this.restaurantOrderStatus;
  }

  /**
   * Sets the value of the 'restaurantOrderStatus' field.
   * @param value the value to set.
   */
  public void setRestaurantOrderStatus(final com.food.ordering.system.kafka.order.avro.model.RestaurantOrderStatus value) {
    this.restaurantOrderStatus = value;
  }

  /**
   * Gets the value of the 'products' field.
   * @return The value of the 'products' field.
   */
  public java.util.List<com.food.ordering.system.kafka.order.avro.model.Product> getProducts() {
    return this.products;
  }

  /**
   * Sets the value of the 'products' field.
   * @param value the value to set.
   */
  public void setProducts(final java.util.List<com.food.ordering.system.kafka.order.avro.model.Product> value) {
    this.products = value;
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
   * RecordBuilder for RestaurantApprovalRequestAvroModel instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<RestaurantApprovalRequestAvroModel>
    implements org.apache.avro.data.RecordBuilder<RestaurantApprovalRequestAvroModel> {

    private java.lang.String id;
    private java.lang.String sagaId;
    private java.lang.String restaurantId;
    private java.lang.String orderId;
    private com.food.ordering.system.kafka.order.avro.model.RestaurantOrderStatus restaurantOrderStatus;
    private java.util.List<com.food.ordering.system.kafka.order.avro.model.Product> products;
    private java.math.BigDecimal price;
    private java.time.Instant createdAt;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(final com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder other) {
      super(other);
      if (isValidValue(this.fields()[0], other.id)) {
        this.id = this.data().deepCopy(this.fields()[0].schema(), other.id);
        this.fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(this.fields()[1], other.sagaId)) {
        this.sagaId = this.data().deepCopy(this.fields()[1].schema(), other.sagaId);
        this.fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(this.fields()[2], other.restaurantId)) {
        this.restaurantId = this.data().deepCopy(this.fields()[2].schema(), other.restaurantId);
        this.fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(this.fields()[3], other.orderId)) {
        this.orderId = this.data().deepCopy(this.fields()[3].schema(), other.orderId);
        this.fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(this.fields()[4], other.restaurantOrderStatus)) {
        this.restaurantOrderStatus = this.data().deepCopy(this.fields()[4].schema(), other.restaurantOrderStatus);
        this.fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(this.fields()[5], other.products)) {
        this.products = this.data().deepCopy(this.fields()[5].schema(), other.products);
        this.fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(this.fields()[6], other.price)) {
        this.price = this.data().deepCopy(this.fields()[6].schema(), other.price);
        this.fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
      if (isValidValue(this.fields()[7], other.createdAt)) {
        this.createdAt = this.data().deepCopy(this.fields()[7].schema(), other.createdAt);
        this.fieldSetFlags()[7] = other.fieldSetFlags()[7];
      }
    }

    /**
     * Creates a Builder by copying an existing RestaurantApprovalRequestAvroModel instance
     * @param other The existing instance to copy.
     */
    private Builder(final com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(this.fields()[0], other.id)) {
        this.id = this.data().deepCopy(this.fields()[0].schema(), other.id);
        this.fieldSetFlags()[0] = true;
      }
      if (isValidValue(this.fields()[1], other.sagaId)) {
        this.sagaId = this.data().deepCopy(this.fields()[1].schema(), other.sagaId);
        this.fieldSetFlags()[1] = true;
      }
      if (isValidValue(this.fields()[2], other.restaurantId)) {
        this.restaurantId = this.data().deepCopy(this.fields()[2].schema(), other.restaurantId);
        this.fieldSetFlags()[2] = true;
      }
      if (isValidValue(this.fields()[3], other.orderId)) {
        this.orderId = this.data().deepCopy(this.fields()[3].schema(), other.orderId);
        this.fieldSetFlags()[3] = true;
      }
      if (isValidValue(this.fields()[4], other.restaurantOrderStatus)) {
        this.restaurantOrderStatus = this.data().deepCopy(this.fields()[4].schema(), other.restaurantOrderStatus);
        this.fieldSetFlags()[4] = true;
      }
      if (isValidValue(this.fields()[5], other.products)) {
        this.products = this.data().deepCopy(this.fields()[5].schema(), other.products);
        this.fieldSetFlags()[5] = true;
      }
      if (isValidValue(this.fields()[6], other.price)) {
        this.price = this.data().deepCopy(this.fields()[6].schema(), other.price);
        this.fieldSetFlags()[6] = true;
      }
      if (isValidValue(this.fields()[7], other.createdAt)) {
        this.createdAt = this.data().deepCopy(this.fields()[7].schema(), other.createdAt);
        this.fieldSetFlags()[7] = true;
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
    public com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder setId(final java.lang.String value) {
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
    public com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder clearId() {
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
    public com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder setSagaId(final java.lang.String value) {
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
    public com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder clearSagaId() {
      this.sagaId = null;
      this.fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'restaurantId' field.
      * @return The value.
      */
    public java.lang.String getRestaurantId() {
      return this.restaurantId;
    }


    /**
      * Sets the value of the 'restaurantId' field.
      * @param value The value of 'restaurantId'.
      * @return This builder.
      */
    public com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder setRestaurantId(final java.lang.String value) {
      this.validate(this.fields()[2], value);
      this.restaurantId = value;
      this.fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'restaurantId' field has been set.
      * @return True if the 'restaurantId' field has been set, false otherwise.
      */
    public boolean hasRestaurantId() {
      return this.fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'restaurantId' field.
      * @return This builder.
      */
    public com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder clearRestaurantId() {
      this.restaurantId = null;
      this.fieldSetFlags()[2] = false;
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
    public com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder setOrderId(final java.lang.String value) {
      this.validate(this.fields()[3], value);
      this.orderId = value;
      this.fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'orderId' field has been set.
      * @return True if the 'orderId' field has been set, false otherwise.
      */
    public boolean hasOrderId() {
      return this.fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'orderId' field.
      * @return This builder.
      */
    public com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder clearOrderId() {
      this.orderId = null;
      this.fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'restaurantOrderStatus' field.
      * @return The value.
      */
    public com.food.ordering.system.kafka.order.avro.model.RestaurantOrderStatus getRestaurantOrderStatus() {
      return this.restaurantOrderStatus;
    }


    /**
      * Sets the value of the 'restaurantOrderStatus' field.
      * @param value The value of 'restaurantOrderStatus'.
      * @return This builder.
      */
    public com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder setRestaurantOrderStatus(final com.food.ordering.system.kafka.order.avro.model.RestaurantOrderStatus value) {
      this.validate(this.fields()[4], value);
      this.restaurantOrderStatus = value;
      this.fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'restaurantOrderStatus' field has been set.
      * @return True if the 'restaurantOrderStatus' field has been set, false otherwise.
      */
    public boolean hasRestaurantOrderStatus() {
      return this.fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'restaurantOrderStatus' field.
      * @return This builder.
      */
    public com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder clearRestaurantOrderStatus() {
      this.restaurantOrderStatus = null;
      this.fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'products' field.
      * @return The value.
      */
    public java.util.List<com.food.ordering.system.kafka.order.avro.model.Product> getProducts() {
      return this.products;
    }


    /**
      * Sets the value of the 'products' field.
      * @param value The value of 'products'.
      * @return This builder.
      */
    public com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder setProducts(final java.util.List<com.food.ordering.system.kafka.order.avro.model.Product> value) {
      this.validate(this.fields()[5], value);
      this.products = value;
      this.fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'products' field has been set.
      * @return True if the 'products' field has been set, false otherwise.
      */
    public boolean hasProducts() {
      return this.fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'products' field.
      * @return This builder.
      */
    public com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder clearProducts() {
      this.products = null;
      this.fieldSetFlags()[5] = false;
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
    public com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder setPrice(final java.math.BigDecimal value) {
      this.validate(this.fields()[6], value);
      this.price = value;
      this.fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'price' field has been set.
      * @return True if the 'price' field has been set, false otherwise.
      */
    public boolean hasPrice() {
      return this.fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'price' field.
      * @return This builder.
      */
    public com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder clearPrice() {
      this.price = null;
      this.fieldSetFlags()[6] = false;
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
    public com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder setCreatedAt(final java.time.Instant value) {
      this.validate(this.fields()[7], value);
      this.createdAt = value.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
      this.fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'createdAt' field has been set.
      * @return True if the 'createdAt' field has been set, false otherwise.
      */
    public boolean hasCreatedAt() {
      return this.fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'createdAt' field.
      * @return This builder.
      */
    public com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalRequestAvroModel.Builder clearCreatedAt() {
      this.fieldSetFlags()[7] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public RestaurantApprovalRequestAvroModel build() {
      try {
        final RestaurantApprovalRequestAvroModel record = new RestaurantApprovalRequestAvroModel();
        record.id = this.fieldSetFlags()[0] ? this.id : (java.lang.String) this.defaultValue(this.fields()[0]);
        record.sagaId = this.fieldSetFlags()[1] ? this.sagaId : (java.lang.String) this.defaultValue(this.fields()[1]);
        record.restaurantId = this.fieldSetFlags()[2] ? this.restaurantId : (java.lang.String) this.defaultValue(this.fields()[2]);
        record.orderId = this.fieldSetFlags()[3] ? this.orderId : (java.lang.String) this.defaultValue(this.fields()[3]);
        record.restaurantOrderStatus = this.fieldSetFlags()[4] ? this.restaurantOrderStatus : (com.food.ordering.system.kafka.order.avro.model.RestaurantOrderStatus) this.defaultValue(this.fields()[4]);
        record.products = this.fieldSetFlags()[5] ? this.products : (java.util.List<com.food.ordering.system.kafka.order.avro.model.Product>) this.defaultValue(this.fields()[5]);
        record.price = this.fieldSetFlags()[6] ? this.price : (java.math.BigDecimal) this.defaultValue(this.fields()[6]);
        record.createdAt = this.fieldSetFlags()[7] ? this.createdAt : (java.time.Instant) this.defaultValue(this.fields()[7]);
        return record;
      } catch (final org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (final java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

}










