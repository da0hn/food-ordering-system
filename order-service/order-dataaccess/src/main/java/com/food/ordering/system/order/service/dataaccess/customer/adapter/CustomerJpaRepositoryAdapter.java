package com.food.ordering.system.order.service.dataaccess.customer.adapter;

import com.food.ordering.system.domain.entity.Customer;
import com.food.ordering.system.order.service.dataaccess.customer.mapper.CustomerDataAccessMapper;
import com.food.ordering.system.order.service.dataaccess.customer.repository.CustomerJpaRepository;
import com.food.ordering.system.order.service.domain.ports.spi.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class CustomerJpaRepositoryAdapter implements CustomerRepository {

  private final CustomerJpaRepository repository;
  private final CustomerDataAccessMapper mapper;


  @Override
  public Optional<Customer> findCustomer(final UUID customerId) {
    return this.repository.findById(customerId)
      .map(this.mapper::customerEntityToCustomer);
  }

}
