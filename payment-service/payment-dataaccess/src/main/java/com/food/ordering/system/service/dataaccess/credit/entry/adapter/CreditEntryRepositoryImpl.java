package com.food.ordering.system.service.dataaccess.credit.entry.adapter;

import com.food.ordering.system.domain.valueobject.CustomerId;
import com.food.ordering.system.payment.domain.entity.CreditEntry;
import com.food.ordering.system.payment.service.domain.ports.spi.repository.CreditEntryRepository;
import com.food.ordering.system.service.dataaccess.credit.entry.mapper.CreditEntryDataAccessMapper;
import com.food.ordering.system.service.dataaccess.credit.entry.repository.CreditEntryJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CreditEntryRepositoryImpl implements CreditEntryRepository {

  private final CreditEntryJpaRepository repository;
  private final CreditEntryDataAccessMapper mapper;

  @Override
  public CreditEntry save(final CreditEntry creditEntry) {
    final var creditEntryEntity = this.mapper.creditEntryToCreditEntryEntity(creditEntry);
    final var savedCreditEntryEntity = this.repository.save(creditEntryEntity);
    return this.mapper.creditEntryEntityToCreditEntry(savedCreditEntryEntity);
  }

  @Override
  public Optional<CreditEntry> findByCustomerId(final CustomerId customerId) {
    return this.repository.findByCustomerId(customerId.getValue())
      .map(this.mapper::creditEntryEntityToCreditEntry);
  }
}
