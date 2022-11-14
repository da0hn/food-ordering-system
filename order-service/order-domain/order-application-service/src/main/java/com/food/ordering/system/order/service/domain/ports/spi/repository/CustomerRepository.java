package com.food.ordering.system.order.service.domain.ports.spi.repository;

import com.food.ordering.system.domain.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

  Optional<Customer> findCustomer(UUID customerId);

}
