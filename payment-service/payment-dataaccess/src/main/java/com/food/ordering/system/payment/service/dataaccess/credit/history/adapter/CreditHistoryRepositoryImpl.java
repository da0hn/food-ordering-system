package com.food.ordering.system.payment.service.dataaccess.credit.history.adapter;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.payment.service.domain.entity.CreditHistory;
import com.food.ordering.system.payment.service.dataaccess.credit.history.mapper.CreditHistoryDataAccessMapper;
import com.food.ordering.system.payment.service.dataaccess.credit.history.repository.CreditHistoryJpaRepository;
import com.food.ordering.system.payment.service.domain.ports.spi.repository.CreditHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CreditHistoryRepositoryImpl implements CreditHistoryRepository {

  private final CreditHistoryJpaRepository repository;
  private final CreditHistoryDataAccessMapper mapper;

  @Override
  public CreditHistory save(final CreditHistory creditHistory) {
    final var creditHistoryEntity = this.mapper.creditHistoryToCreditHistoryEntity(creditHistory);
    final var savedCreditHistoryEntity = this.repository.save(creditHistoryEntity);
    return this.mapper.creditHistoryEntityToCreditHistory(savedCreditHistoryEntity);
  }

  @Override
  public Optional<List<CreditHistory>> findByCustomerId(final CustomerId customerId) {
    return this.repository.findByCustomerId(customerId.getValue())
      .map(histories ->
             histories.stream()
               .map(this.mapper::creditHistoryEntityToCreditHistory)
               .collect(Collectors.toList())
      );
  }
}
