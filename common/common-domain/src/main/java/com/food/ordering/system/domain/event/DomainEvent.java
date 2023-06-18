package com.food.ordering.system.domain.event;

@FunctionalInterface
public interface DomainEvent<T> {

  void fire();

}
