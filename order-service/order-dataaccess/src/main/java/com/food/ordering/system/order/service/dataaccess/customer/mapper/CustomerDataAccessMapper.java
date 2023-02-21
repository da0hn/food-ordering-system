package com.food.ordering.system.order.service.dataaccess.customer.mapper;

import com.food.ordering.system.domain.entity.Customer;
import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.order.service.dataaccess.customer.entity.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerDataAccessMapper {

  public Customer customerEntityToCustomer(final CustomerEntity customerEntity) {
    return new Customer(CustomerId.of(customerEntity.getId()));
  }

}
