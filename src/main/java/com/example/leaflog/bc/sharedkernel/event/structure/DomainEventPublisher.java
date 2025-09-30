package com.example.leaflog.bc.sharedkernel.event.structure;

public interface DomainEventPublisher {
    void publish(Object event);
}
