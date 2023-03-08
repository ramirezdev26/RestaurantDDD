package org.example.business.commons;

import org.example.generic.DomainEvent;

import java.util.List;

public interface EventsRepository {

    DomainEvent saveEvent(DomainEvent event);

    List<DomainEvent> findByAggregatedRootId(String aggregatedRootId);

}
