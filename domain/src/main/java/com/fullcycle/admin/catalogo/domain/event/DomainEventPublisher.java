package com.fullcycle.admin.catalogo.domain.event;

@FunctionalInterface
public interface DomainEventPublisher {

     void publishEvent(final DomainEvent event);
}
