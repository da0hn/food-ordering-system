package com.food.ordering.system.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public class StreetAddress {

  private final UUID id;
  private final String street;
  private final String postalCode;
  private final String city;

  public StreetAddress(
    final UUID id,
    final String street,
    final String postalCode,
    final String city
  ) {
    this.id = id;
    this.street = street;
    this.postalCode = postalCode;
    this.city = city;
  }

  public UUID getId() {
    return this.id;
  }

  public String getStreet() {
    return this.street;
  }

  public String getPostalCode() {
    return this.postalCode;
  }

  public String getCity() {
    return this.city;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.street, this.postalCode, this.city);
  }

  @Override
  public boolean equals(final Object o) {
    if(this == o) return true;
    if(o == null || this.getClass() != o.getClass()) return false;
    final StreetAddress that = (StreetAddress) o;
    return this.street.equals(that.street) && this.postalCode.equals(that.postalCode) && this.city.equals(that.city);
  }

}
